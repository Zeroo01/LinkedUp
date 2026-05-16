package com.example.linkedup.repository

import com.example.linkedup.objects.Event

object EventRepository {

    val events = listOf(
        Event(
            id = "1",
            title = "Tech Career Expo Berlin",
            description = "Recruiting Event",
            imageUrl = "",
            isActive = true,
            startTime = System.currentTimeMillis(),
            timeline = listOf()
        )
    )

    fun getActiveEvent(): Event? =
        events.find { it.isActive }
}