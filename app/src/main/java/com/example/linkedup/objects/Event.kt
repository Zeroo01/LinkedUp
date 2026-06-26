package com.example.linkedup.objects

data class Event(

    val id: String = "",

    val title: String = "",

    val description: String = "",

    val location: String = "",

    val date: String = "",

    val imageUrl: String = "",

    val isActive: Boolean = false,

    val presentations: List<Presentation> = emptyList()
)