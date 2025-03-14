package com.example.videolog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videolog.data.MockVideoData
import com.example.videolog.model.VideoEpisode
import com.example.videolog.model.VideoSeries
import com.example.videolog.ui.adapters.VideoEpisodeAdapter

class EpisodeListActivity : AppCompatActivity() {
    
    companion object {
        const val EXTRA_SERIES_ID = "extra_series_id"
        const val EXTRA_EPISODE_ID = "extra_episode_id"
    }
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var seriesTitleTextView: TextView
    private lateinit var seriesDescriptionTextView: TextView
    private lateinit var adapter: VideoEpisodeAdapter
    private lateinit var currentSeries: VideoSeries
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_list)
        
        // Get series ID from intent
        val seriesId = intent.getStringExtra(EXTRA_SERIES_ID)
        if (seriesId == null) {
            Toast.makeText(this, "Series ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // Find the series in mock data
        val allSeries = MockVideoData.getVideoSeries()
        currentSeries = allSeries.find { it.id == seriesId } ?: run {
            Toast.makeText(this, "Series not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // Initialize views
        seriesTitleTextView = findViewById(R.id.seriesTitleTextView)
        seriesDescriptionTextView = findViewById(R.id.seriesDescriptionTextView)
        recyclerView = findViewById(R.id.episodesRecyclerView)
        
        // Set series info
        seriesTitleTextView.text = currentSeries.title
        seriesDescriptionTextView.text = currentSeries.description
        
        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        // Set up adapter
        adapter = VideoEpisodeAdapter(currentSeries.episodes) { episode ->
            // In a real app, this would navigate to a video player activity
            Toast.makeText(
                this,
                "Playing episode: ${episode.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        
        recyclerView.adapter = adapter
    }
}
