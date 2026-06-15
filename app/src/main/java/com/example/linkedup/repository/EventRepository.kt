package com.example.linkedup.repository

import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation
object EventRepository {

    private val events = listOf(
        Event(
            id = "1",
            title = "Test Event titel",
            description = "filler",
            imageUrl = "",
            isActive = true,
            presentations = emptyList()
        )
    )

    fun getActiveEvent(): Event? {
        return events.find { it.isActive }
    }

    fun getEventById(id: String): Event? {
        return events.find { it.id == id }
    }
}