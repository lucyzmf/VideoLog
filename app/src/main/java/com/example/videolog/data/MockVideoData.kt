package com.example.videolog.data

import com.example.videolog.R
import com.example.videolog.model.VideoEpisode
import com.example.videolog.model.VideoSeries

/**
 * Mock data provider for video series and episodes
 */
object MockVideoData {
    
    fun getVideoSeries(): List<VideoSeries> {
        return listOf(
            VideoSeries(
                id = "series1",
                title = "Nature Documentaries",
                description = "Explore the wonders of nature with these stunning documentaries",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = getNatureEpisodes(),
                progress = 0.3f
            ),
            VideoSeries(
                id = "series2",
                title = "Space Exploration",
                description = "Journey through the cosmos with these space documentaries",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = getSpaceEpisodes(),
                progress = 0.7f
            ),
            VideoSeries(
                id = "series3",
                title = "Technology Insights",
                description = "Discover the latest in technology and innovation",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = getTechEpisodes(),
                progress = 0.1f
            ),
            VideoSeries(
                id = "series4",
                title = "Historical Events",
                description = "Relive the most significant moments in history",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = getHistoryEpisodes(),
                progress = 0.5f
            )
        )
    }
    
    private fun getNatureEpisodes(): List<VideoEpisode> {
        return listOf(
            VideoEpisode(
                id = "nature1",
                seriesId = "series1",
                title = "Rainforest Secrets",
                description = "Discover the hidden life in rainforests around the world",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 1800000, // 30 minutes
                watchedPosition = 900000 // 15 minutes
            ),
            VideoEpisode(
                id = "nature2",
                seriesId = "series1",
                title = "Ocean Depths",
                description = "Explore the mysterious creatures of the deep ocean",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 2400000, // 40 minutes
                watchedPosition = 600000 // 10 minutes
            ),
            VideoEpisode(
                id = "nature3",
                seriesId = "series1",
                title = "Desert Life",
                description = "How animals and plants survive in the harshest environments",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 1500000, // 25 minutes
                watchedPosition = 0 // Not started
            )
        )
    }
    
    private fun getSpaceEpisodes(): List<VideoEpisode> {
        return listOf(
            VideoEpisode(
                id = "space1",
                seriesId = "series2",
                title = "Mars: The Red Planet",
                description = "Everything we know about our neighboring planet",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 2700000, // 45 minutes
                watchedPosition = 2700000 // Fully watched
            ),
            VideoEpisode(
                id = "space2",
                seriesId = "series2",
                title = "Black Holes Explained",
                description = "The science behind the universe's most mysterious objects",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 1800000, // 30 minutes
                watchedPosition = 900000 // Half watched
            )
        )
    }
    
    private fun getTechEpisodes(): List<VideoEpisode> {
        return listOf(
            VideoEpisode(
                id = "tech1",
                seriesId = "series3",
                title = "AI Revolution",
                description = "How artificial intelligence is changing our world",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 2100000, // 35 minutes
                watchedPosition = 300000 // 5 minutes
            ),
            VideoEpisode(
                id = "tech2",
                seriesId = "series3",
                title = "Quantum Computing",
                description = "Understanding the next generation of computing",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 2400000, // 40 minutes
                watchedPosition = 0 // Not started
            )
        )
    }
    
    private fun getHistoryEpisodes(): List<VideoEpisode> {
        return listOf(
            VideoEpisode(
                id = "history1",
                seriesId = "series4",
                title = "Ancient Egypt",
                description = "The rise and fall of the pharaohs",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 3000000, // 50 minutes
                watchedPosition = 1800000 // 30 minutes
            ),
            VideoEpisode(
                id = "history2",
                seriesId = "series4",
                title = "World War II",
                description = "A comprehensive look at the global conflict",
                thumbnailResId = R.drawable.ic_launcher_background,
                videoUri = "asset:///sample_video.mp4",
                duration = 3600000, // 60 minutes
                watchedPosition = 1200000 // 20 minutes
            )
        )
    }
}
