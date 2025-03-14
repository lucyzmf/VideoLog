package com.example.videolog

import android.content.Context
import android.content.res.AssetManager
import com.google.common.truth.Truth.assertThat
import org.mockito.Mockito.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream

class ConfigLoaderTest {

    @Test
    fun loadConfig_validConfig_returnsConfigObject() {
        // Arrange
        val validJson = """
            {
                "version": 1,
                "video": {
                    "uri": "",
                    "playback_speed": 1.0,
                    "loop": false
                },
                "serial": {
                    "baud_rate": 115200,
                    "data_bits": 8,
                    "stop_bits": 1,
                    "parity": "none"
                },
                "logging": {
                    "enabled": true,
                    "directory": "logs",
                    "max_file_size_mb": 10
                }
            }
        """.trimIndent()
        
        val schemaJson = """
            {
              "${'$'}schema": "http://json-schema.org/draft-07/schema#",
              "type": "object",
              "required": ["version", "video", "serial", "logging"],
              "properties": {
                "version": {
                  "type": "integer",
                  "minimum": 1
                },
                "video": {
                  "type": "object",
                  "required": ["uri", "playback_speed", "loop"],
                  "properties": {
                    "uri": {
                      "type": "string"
                    },
                    "playback_speed": {
                      "type": "number",
                      "minimum": 0.1,
                      "maximum": 10.0
                    },
                    "loop": {
                      "type": "boolean"
                    }
                  }
                },
                "serial": {
                  "type": "object",
                  "required": ["baud_rate", "data_bits", "stop_bits", "parity"],
                  "properties": {
                    "baud_rate": {
                      "type": "integer",
                      "enum": [9600, 19200, 38400, 57600, 115200]
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
                      "enum": ["none", "odd", "even", "mark", "space"]
                    }
                  }
                },
                "logging": {
                  "type": "object",
                  "required": ["enabled", "directory", "max_file_size_mb"],
                  "properties": {
                    "enabled": {
                      "type": "boolean"
                    },
                    "directory": {
                      "type": "string"
                    },
                    "max_file_size_mb": {
                      "type": "integer",
                      "minimum": 1,
                      "maximum": 100
                    }
                  }
                }
              }
            }
        """.trimIndent()
        
        // Create mock AssetManager
        val mockAssetManager = mock(AssetManager::class.java)
        
        // When opening config.json, return our valid JSON
        `when`(mockAssetManager.open("config.json")).thenReturn(ByteArrayInputStream(validJson.toByteArray()))
        
        // When opening config_schema.json, return the schema
        `when`(mockAssetManager.open("config_schema.json")).thenReturn(ByteArrayInputStream(schemaJson.toByteArray()))
        
        // Create mock Context
        val mockContext = mock(Context::class.java)
        `when`(mockContext.assets).thenReturn(mockAssetManager)
        
        val configLoader = ConfigLoader(mockContext)

        // Act
        val config = configLoader.loadConfig()

        // Assert
        assertThat(config).isNotNull()
        assertThat(config.version).isEqualTo(1)
        assertThat(config.video.uri).isEmpty()
        assertThat(config.video.playback_speed).isEqualTo(1.0f)
        assertThat(config.serial.baud_rate).isEqualTo(115200)
        assertThat(config.logging.max_file_size_mb).isEqualTo(10)
    }

    @Test(expected = ConfigValidationException::class)
    fun loadConfig_invalidConfig_throwsException() {
        // Arrange
        val invalidJson = """
            {
                "version": 1,
                "video": {
                    "uri": "",
                    "playback_speed": 1.0
                },
                "serial": {
                    "baud_rate": 115200,
                    "data_bits": 8,
                    "stop_bits": 1,
                    "parity": "none"
                },
                "logging": {
                    "enabled": true,
                    "directory": "logs",
                    "max_file_size_mb": 10
                }
            }
        """.trimIndent()
        
        val schemaJson = """
            {
              "${'$'}schema": "http://json-schema.org/draft-07/schema#",
              "type": "object",
              "required": ["version", "video", "serial", "logging"],
              "properties": {
                "version": {
                  "type": "integer",
                  "minimum": 1
                },
                "video": {
                  "type": "object",
                  "required": ["uri", "playback_speed", "loop"],
                  "properties": {
                    "uri": {
                      "type": "string"
                    },
                    "playback_speed": {
                      "type": "number",
                      "minimum": 0.1,
                      "maximum": 10.0
                    },
                    "loop": {
                      "type": "boolean"
                    }
                  }
                },
                "serial": {
                  "type": "object",
                  "required": ["baud_rate", "data_bits", "stop_bits", "parity"],
                  "properties": {
                    "baud_rate": {
                      "type": "integer",
                      "enum": [9600, 19200, 38400, 57600, 115200]
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
                      "enum": ["none", "odd", "even", "mark", "space"]
                    }
                  }
                },
                "logging": {
                  "type": "object",
                  "required": ["enabled", "directory", "max_file_size_mb"],
                  "properties": {
                    "enabled": {
                      "type": "boolean"
                    },
                    "directory": {
                      "type": "string"
                    },
                    "max_file_size_mb": {
                      "type": "integer",
                      "minimum": 1,
                      "maximum": 100
                    }
                  }
                }
              }
            }
        """.trimIndent()
        
        // Create mock AssetManager
        val mockAssetManager = mock(AssetManager::class.java)
        
        // When opening config.json, return our invalid JSON
        `when`(mockAssetManager.open("config.json")).thenReturn(ByteArrayInputStream(invalidJson.toByteArray()))
        
        // When opening config_schema.json, return the schema
        `when`(mockAssetManager.open("config_schema.json")).thenReturn(ByteArrayInputStream(schemaJson.toByteArray()))
        
        // Create mock Context
        val mockContext = mock(Context::class.java)
        `when`(mockContext.assets).thenReturn(mockAssetManager)
        
        val configLoader = ConfigLoader(mockContext)

        // Act
        configLoader.loadConfig()
    }
}
