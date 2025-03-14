package com.example.videolog

data class AppConfig(
    val version: Int,
    val video: VideoConfig,
    val serial: SerialConfig,
    val logging: LoggingConfig
)

data class VideoConfig(
    val uri: String,
    val playback_speed: Float,
    val loop: Boolean
)

data class SerialConfig(
    val baud_rate: Int,
    val data_bits: Int,
    val stop_bits: Int,
    val parity: String
) {
    init {
        require(baud_rate in listOf(9600, 19200, 38400, 57600, 115200)) {
            "Invalid baud rate"
        }
        require(data_bits in 5..8) { "Data bits must be between 5 and 8" }
        require(stop_bits in 1..2) { "Stop bits must be 1 or 2" }
        require(parity in listOf("none", "odd", "even", "mark", "space")) {
            "Invalid parity setting"
        }
    }
}

data class LoggingConfig(
    val enabled: Boolean,
    val directory: String,
    val max_file_size_mb: Int
) {
    init {
        require(max_file_size_mb in 1..100) {
            "Max file size must be between 1 and 100 MB"
        }
    }
}
