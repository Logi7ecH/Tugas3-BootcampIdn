package com.bootcamp.tugas3_bootcampidn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.tugas3_bootcampidn.api.ApiConfig
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiConfig.getService().getNews().enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful){
                    val responseNews = response.body()
                    val newsData = responseNews?.articles
                    val newsAdapter = NewsAdapter()
                    newsAdapter.setData(newsData as List<ArticlesItem>)

                    binding.rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = newsAdapter
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Gagal", "onFailure: " + t.localizedMessage)
            }
        })
    }
}