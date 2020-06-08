package com.iflippie.level6_task2.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.iflippie.level6_task2.R
import com.iflippie.level6_task2.main.MainActivity
import com.iflippie.level6_task2.model.MovieItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val obj = intent.getParcelableExtra(MainActivity.INTENT_EXTRA_MOVIE) as MovieItem
        Glide.with(this).load(obj.getPosterPath()).into(ivPoster)
        Glide.with(this).load(obj.getBackdropPath()).into(ivBackdrop)
        tvTitle.text = obj.title
        tvRating.text = obj.vote_average.toString()
        tvRelease.text = String.format(getString(R.string.release_date), obj.release_date)
        tvOverview.text = obj.overview

    }
}
