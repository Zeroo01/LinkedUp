package com.example.linkedup.objects

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val presentations: List<Presentation>
)