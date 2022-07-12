package com.borisov.reddit.data

import com.borisov.reddit.domain.RedditPost
import io.reactivex.rxjava3.core.Single

/**
 * @author Borisov Andrey on 07.07.2022
 **/
interface Repository {
    fun getPosts(): Single<List<RedditPost>>
}