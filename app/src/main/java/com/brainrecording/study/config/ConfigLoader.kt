package com.brainrecording.study.config

import android.content.Context
import org.json.JSONObject
import org.json.JSONTokener
import java.io.InputStream

class ConfigLoader(private val context: Context) {

    fun loadConfig(): JSONObject {
        val inputStream: InputStream = context.assets.open("config.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonConfig = JSONObject(JSONTokener(jsonString))
        
        // Validate against schema
        ConfigSchema.schema.validate(jsonConfig)
        
        return jsonConfig
    }
}
