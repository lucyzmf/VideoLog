package com.example.videolog.model

data class VideoSeries(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailResId: Int, // Resource ID for the thumbnail image
    val episodes: List<VideoEpisode>,
    val progress: Float = 0f // 0.0 to 1.0 representing watch progress
)

data class VideoEpisode(
    val id: String,
    val seriesId: String,
    val title: String,
    val description: String,
    val thumbnailResId: Int, // Resource ID for the thumbnail image
    val videoUri: String,
    val duration: Long, // in milliseconds
    val watchedPosition: Long = 0 // in milliseconds
) {
    val progress: Float
        get() = if (duration > 0) watchedPosition.toFloat() / duration else 0f
}
