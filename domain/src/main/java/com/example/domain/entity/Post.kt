package com.example.domain.entity

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
)
