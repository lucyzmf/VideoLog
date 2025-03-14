package com.example.videolog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.videolog.ui.theme.VideoLogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoLogTheme {
        Greeting("Android")
    }
}package com.example.videolog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videolog.data.MockVideoData
import com.example.videolog.model.VideoSeries
import com.example.videolog.ui.adapters.VideoSeriesAdapter

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideoSeriesAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.seriesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns
        
        // Get mock data
        val videoSeries = MockVideoData.getVideoSeries()
        
        // Set up adapter
        adapter = VideoSeriesAdapter(videoSeries) { series ->
            navigateToEpisodeList(series)
        }
        
        recyclerView.adapter = adapter
    }
    
    private fun navigateToEpisodeList(series: VideoSeries) {
        val intent = Intent(this, EpisodeListActivity::class.java).apply {
            putExtra(EpisodeListActivity.EXTRA_SERIES_ID, series.id)
        }
        startActivity(intent)
    }
}
