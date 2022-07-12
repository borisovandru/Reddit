package com.borisov.reddit.data

import com.borisov.reddit.data.network.ApiService
import com.borisov.reddit.domain.RedditPost
import com.borisov.reddit.domain.mapperResponseToPosts
import io.reactivex.rxjava3.core.Single

/**
 * @author Borisov Andrey on 07.07.2022
 **/
class RemoteRepositoryImpl(private val repo: ApiService) : Repository {

    var after: String = ""

    override fun getPosts(): Single<List<RedditPost>> = repo.getPosts(after).map {
        this.after = it.data.after
        mapperResponseToPosts(it)
    }
}