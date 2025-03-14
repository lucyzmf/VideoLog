package com.example.videolog

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.videolog.model.VideoEpisode
import com.example.videolog.model.VideoSeries
import com.example.videolog.ui.adapters.VideoSeriesAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class VideoSeriesAdapterTest {

    private lateinit var mockSeries: List<VideoSeries>
    private lateinit var clickListener: (VideoSeries) -> Unit
    
    @Before
    fun setUp() {
        // Create mock data
        mockSeries = listOf(
            VideoSeries(
                id = "test1",
                title = "Test Series 1",
                description = "Test Description 1",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = emptyList(),
                progress = 0.5f
            ),
            VideoSeries(
                id = "test2",
                title = "Test Series 2",
                description = "Test Description 2",
                thumbnailResId = R.drawable.ic_launcher_background,
                episodes = emptyList(),
                progress = 0.75f
            )
        )
        
        // Mock click listener
        clickListener = mock()
    }
    
    @Test
    fun testItemCount() {
        val adapter = VideoSeriesAdapter(mockSeries, clickListener)
        assertEquals(2, adapter.itemCount)
    }
}
