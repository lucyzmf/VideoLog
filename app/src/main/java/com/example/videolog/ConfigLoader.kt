package com.example.videolog

import android.content.Context
import com.fasterxml.jackson.module.kotlin.*
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import java.io.InputStream

class ConfigLoader(private val context: Context) {

    private val mapper = jacksonObjectMapper()
    private val schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)

    fun loadConfig(): AppConfig {
        val jsonStream = context.assets.open("config.json")
        val schemaStream = context.assets.open("config_schema.json")
        
        // Read the JSON content as a string first to avoid stream issues
        val jsonString = jsonStream.bufferedReader().use { it.readText() }
        val schemaString = schemaStream.bufferedReader().use { it.readText() }
        
        // Parse the JSON
        val jsonNode = mapper.readTree(jsonString)
        
        // Create schema from the string
        val schema = schemaFactory.getSchema(schemaString)
        val errors = schema.validate(jsonNode)
        
        if (errors.isNotEmpty()) {
            throw ConfigValidationException(
                "Config validation failed: ${errors.joinToString("\n")}"
            )
        }

        // Parse the config from the string
        return mapper.readValue<AppConfig>(jsonString)
    }
}

class ConfigValidationException(message: String) : Exception(message)
