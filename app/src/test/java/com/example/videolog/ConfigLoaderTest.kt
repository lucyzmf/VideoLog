package com.example.videolog

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.mockito.Mockito.*
import org.mockito.kotlin.anyString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigLoaderTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun loadConfig_validConfig_returnsConfigObject() {
        // Arrange
        val configLoader = ConfigLoader(context)

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
                    // Missing loop field
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
        
        // Create mock Context and AssetManager
        val mockAssetManager = mock(android.content.res.AssetManager::class.java)
        `when`(mockAssetManager.open(anyString())).thenReturn(invalidJson.byteInputStream())
        
        val mockContext = mock(Context::class.java)
        `when`(mockContext.assets).thenReturn(mockAssetManager)
        
        val configLoader = ConfigLoader(mockContext)

        // Act
        configLoader.loadConfig()
    }
}
