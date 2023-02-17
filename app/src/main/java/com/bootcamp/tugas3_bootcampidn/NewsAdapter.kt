package com.bootcamp.tugas3_bootcampidn

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.tugas3_bootcampidn.databinding.ItemRowNewsBinding
import com.bumptech.glide.Glide
import java.time.format.DateTimeFormatter

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsData: List<ArticlesItem> = listOf()

    inner class NewsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRowNewsBinding.bind(view)
        fun bind(news: ArticlesItem) {
            binding.apply {
                val date = news.publishedAt?.substring(0,10)
                tvJudul.text = news.title
                tvPenulis.text = news.author
                tvTanggalPosting.text = date

                Glide.with(imgNews)
                    .load(news.urlToImage)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgNews)

                binding.cardNews.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS, newsData[adapterPosition])
                    itemView.context.startActivity(intent)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsData.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsData[position])

    }

    fun setData(data: List<ArticlesItem>){
        newsData = data
        notifyDataSetChanged()
    }
}