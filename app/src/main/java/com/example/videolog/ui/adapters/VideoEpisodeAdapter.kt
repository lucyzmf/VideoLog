package com.example.videolog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videolog.R
import com.example.videolog.model.VideoEpisode
import java.util.concurrent.TimeUnit

class VideoEpisodeAdapter(
    private val episodes: List<VideoEpisode>,
    private val onItemClick: (VideoEpisode) -> Unit
) : RecyclerView.Adapter<VideoEpisodeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.episodeTitleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.episodeDescriptionTextView)
        val thumbnailImageView: ImageView = view.findViewById(R.id.episodeThumbnailImageView)
        val progressBar: ProgressBar = view.findViewById(R.id.episodeProgressBar)
        val durationTextView: TextView = view.findViewById(R.id.episodeDurationTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_episode, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        
        holder.titleTextView.text = episode.title
        holder.descriptionTextView.text = episode.description
        holder.thumbnailImageView.setImageResource(episode.thumbnailResId)
        
        // Set progress
        holder.progressBar.progress = (episode.progress * 100).toInt()
        
        // Format duration
        val minutes = TimeUnit.MILLISECONDS.toMinutes(episode.duration)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(episode.duration) - 
                TimeUnit.MINUTES.toSeconds(minutes)
        holder.durationTextView.text = String.format("%d:%02d", minutes, seconds)
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(episode)
        }
    }

    override fun getItemCount() = episodes.size
}
