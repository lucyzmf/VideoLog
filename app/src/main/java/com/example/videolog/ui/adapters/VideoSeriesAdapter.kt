package com.example.videolog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videolog.R
import com.example.videolog.model.VideoSeries

class VideoSeriesAdapter(
    private val videoSeries: List<VideoSeries>,
    private val onItemClick: (VideoSeries) -> Unit
) : RecyclerView.Adapter<VideoSeriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.seriesTitleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.seriesDescriptionTextView)
        val thumbnailImageView: ImageView = view.findViewById(R.id.seriesThumbnailImageView)
        val progressBar: ProgressBar = view.findViewById(R.id.seriesProgressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_series, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = videoSeries[position]
        
        holder.titleTextView.text = series.title
        holder.descriptionTextView.text = series.description
        holder.thumbnailImageView.setImageResource(series.thumbnailResId)
        
        // Set progress
        holder.progressBar.progress = (series.progress * 100).toInt()
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(series)
        }
    }

    override fun getItemCount() = videoSeries.size
}
