package com.example.linkedup.repository

import com.example.linkedup.objects.Event
import com.example.linkedup.objects.EventItem

object EventRepository {

    val events = listOf(
        Event(
            id = "1",
            title = "Tech Career Expo Berlin",
            description = "Das größte Recruiting Event für Tech Talente.",
            imageUrl = "https://picsum.photos/800/400",
            isActive = true,
            timeline = listOf(
                EventItem("10:00", "Opening Keynote", "Talk"),
                EventItem("11:00", "Google Workshop", "Workshop"),
                EventItem("13:00", "Startup Pitches", "Pitch")
            )
        )
    )

    fun getActiveEvent(): Event? =
        events.find { it.isActive }
}