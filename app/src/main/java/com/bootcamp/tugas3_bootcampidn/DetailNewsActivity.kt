package com.bootcamp.tugas3_bootcampidn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityDetailNewsBinding
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailNewsBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailNewsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

		binding.apply {
			tvJudul.text = news?.title
			tvDeskripsi.text = news?.description

			Glide.with(imgNews)
				.load(news?.urlToImage)
				.error(R.drawable.ic_launcher_background)
				.into(imgNews)
		}
	}

	companion object{
		const val EXTRA_NEWS = "news"
	}
}