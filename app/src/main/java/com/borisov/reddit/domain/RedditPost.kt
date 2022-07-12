package com.borisov.reddit.domain

/**
 * @author Borisov Andrey on 07.07.2022
 **/
data class RedditPost(
    val title: String,
    val score: Int,
    val comments: Int,
)
