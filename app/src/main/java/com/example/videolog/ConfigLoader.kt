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
        
        // Validate against schema
        val schema = schemaFactory.getSchema(schemaStream)
        val jsonNode = mapper.readTree(jsonStream)
        val errors = schema.validate(jsonNode)
        
        if (errors.isNotEmpty()) {
            throw ConfigValidationException(
                "Config validation failed: ${errors.joinToString("\n")}"
            )
        }

        return mapper.readValue<AppConfig>(jsonStream)
    }
}

class ConfigValidationException(message: String) : Exception(message)
