package com.borisov.reddit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borisov.reddit.data.RemoteRepositoryImpl
import com.borisov.reddit.data.Repository
import com.borisov.reddit.data.network.Retrofit
import com.borisov.reddit.domain.RedditPost
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author Borisov Andrey on 07.07.2022
 **/
class MainViewModel(private val repo: Repository = RemoteRepositoryImpl(Retrofit().getService())) :
    ViewModel() {

    private val liveDataToObserve: MutableLiveData<List<RedditPost>> = MutableLiveData(listOf())

    fun getData(): LiveData<List<RedditPost>> = liveDataToObserve

    fun requestPosts() = requestPostsFromApi()

    private fun requestPostsFromApi() {
        repo.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataToObserve.postValue(it)
            }, {
            })
    }
}