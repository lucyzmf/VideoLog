package com.brainrecording.study.config

import org.json.JSONObject
import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONTokener

object ConfigSchema {
    private val schemaJson = """
    {
        "\$schema": "http://json-schema.org/draft-07/schema#",
        "type": "object",
        "required": ["version", "video_settings", "speech_settings", "serial_settings"],
        "properties": {
            "version": {
                "type": "integer",
                "minimum": 1
            },
            "video_settings": {
                "type": "object",
                "required": ["source", "loop", "volume"],
                "properties": {
                    "source": {"type": "string"},
                    "loop": {"type": "boolean"},
                    "volume": {
                        "type": "number",
                        "minimum": 0,
                        "maximum": 1
                    }
                }
            },
            "speech_settings": {
                "type": "object",
                "required": ["language", "pitch", "speed"],
                "properties": {
                    "language": {"type": "string"},
                    "pitch": {
                        "type": "number",
                        "minimum": 0.5,
                        "maximum": 2.0
                    },
                    "speed": {
                        "type": "number",
                        "minimum": 0.5,
                        "maximum": 2.0
                    }
                }
            },
            "serial_settings": {
                "type": "object",
                "required": ["baud_rate", "data_bits", "stop_bits", "parity"],
                "properties": {
                    "baud_rate": {
                        "type": "integer",
                        "minimum": 9600,
                        "maximum": 115200
                    },
                    "data_bits": {
                        "type": "integer",
                        "minimum": 5,
                        "maximum": 8
                    },
                    "stop_bits": {
                        "type": "integer",
                        "minimum": 1,
                        "maximum": 2
                    },
                    "parity": {
                        "type": "string",
                        "enum": ["none", "odd", "even"]
                    }
                }
            }
        }
    }
    """.trimIndent()

    val schema: Schema by lazy {
        val rawSchema = JSONObject(JSONTokener(schemaJson))
        SchemaLoader.load(rawSchema)
    }
}
