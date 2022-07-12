package com.borisov.reddit.domain

/**
 * @author Borisov Andrey on 07.07.2022
 **/
fun mapperResponseToPosts(response: ApiResponse) = response.data.children.map {
    RedditPost(
        it.data?.title ?: "Title", it.data?.score ?: 0,
        (it.data?.comments?.toInt() ?: "0") as Int
    )
}