package com.brainrecording.study.config

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigLoaderTest {

    private lateinit var context: Context
    private lateinit var configLoader: ConfigLoader

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        configLoader = ConfigLoader(context)
    }

    @Test
    fun testValidConfig() {
        val config = configLoader.loadConfig()
        assertNotNull(config)
        assertEquals(1, config.getInt("version"))
    }

    @Test(expected = Exception::class)
    fun testMissingBaudRate() {
        val invalidJson = """
        {
            "version": 1,
            "video_settings": {
                "source": "file:///android_asset/sample.mp4",
                "loop": true,
                "volume": 0.8
            },
            "speech_settings": {
                "language": "en-US",
                "pitch": 1.0,
                "speed": 1.0
            },
            "serial_settings": {
                "data_bits": 8,
                "stop_bits": 1,
                "parity": "none"
            }
        }
        """.trimIndent()
        
        ConfigSchema.schema.validate(JSONObject(invalidJson))
    }
}
