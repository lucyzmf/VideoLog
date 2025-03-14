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
        
        // Read the JSON content
        val jsonNode = mapper.readTree(jsonStream)
        jsonStream.reset() // Reset stream for later use
        
        // Validate against schema
        val schema = schemaFactory.getSchema(schemaStream)
        val errors = schema.validate(jsonNode)
        
        if (errors.isNotEmpty()) {
            throw ConfigValidationException(
                "Config validation failed: ${errors.joinToString("\n")}"
            )
        }

        // Reset the stream and read the value
        return mapper.readValue<AppConfig>(context.assets.open("config.json"))
    }
}

class ConfigValidationException(message: String) : Exception(message)
