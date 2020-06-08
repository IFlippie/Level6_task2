package com.iflippie.level6_task2.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iflippie.level6_task2.model.MovieItem
import com.iflippie.level6_task2.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movieList: List<MovieItem>, private val onClick: (MovieItem) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                onClick(movieList[adapterPosition])
            }
        }
        fun bind(movieItem: MovieItem) {
            itemView.tvMovieNumber.text = movieItem.title
            Glide.with(context).load(movieItem.getPosterPath()).into(itemView.ivMovieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}
