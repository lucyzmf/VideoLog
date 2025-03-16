package com.example.videolog

import android.content.Context
import com.fasterxml.jackson.module.kotlin.*
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import java.io.InputStream

// Class responsible for loading and validating configuration settings
class ConfigLoader(private val context: Context) {

    // Jackson object mapper to handle JSON parsing
    private val mapper = jacksonObjectMapper()

    // JSON schema factory to validate config files
    private val schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)

    // Function to load the app configuration
    fun loadConfig(): AppConfig {
        // Read the config.json file from assets as a string
        val jsonString = context.assets.open("config.json").use { stream ->
            stream.bufferedReader().readText()
        }

        // Read the JSON schema file (config_schema.json) from assets
        val schemaString = context.assets.open("config_schema.json").use { stream ->
            stream.bufferedReader().readText()
        }

        // Parse the JSON file into a Jackson JSON tree
        val jsonNode = mapper.readTree(jsonString)

        // Load and create the JSON schema from schemaString
        val schema = schemaFactory.getSchema(schemaString)

        // Validate the config JSON against the schema
        val errors = schema.validate(jsonNode)

        // If there are validation errors, throw an exception with error details
        if (errors.isNotEmpty()) {
            throw ConfigValidationException(
                "Config validation failed: ${errors.joinToString("\n")}"
            )
        }

        // If validation passes, parse the JSON string into an AppConfig object
        return mapper.readValue<AppConfig>(jsonString)
    }
}

// Custom exception to handle configuration validation errors
class ConfigValidationException(message: String) : Exception(message)