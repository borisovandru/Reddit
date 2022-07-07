package com.borisov.reddit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borisov.reddit.databinding.ActivityMainBinding
import com.borisov.reddit.domain.RedditPost

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val adapter = MainAdapter()

    var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initRecycler()
    }

    private fun initRecycler() {
        val observer = Observer<List<RedditPost>> {
            adapter.setData(it)
        }
        val manager = LinearLayoutManager(this)
        bind.recycler.layoutManager = manager
        bind.recycler.adapter = adapter
        viewModel.getData().observe(this, observer)
        viewModel.requestPosts()
        bind.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) isScrolling =
                    true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isScrolling && (manager.childCount + manager.findFirstVisibleItemPosition() >= manager.itemCount - 3)) {
                    isScrolling = false
                    viewModel.requestPosts()
                }
            }
        })
    }
}