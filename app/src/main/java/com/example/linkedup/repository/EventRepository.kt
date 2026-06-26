package com.example.linkedup.repository

import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation

object EventRepository {

    private val events = listOf(

        Event(
            id = "1",
            title = "Test Event Titel",
            description = "Filler",
            location = "Berlin",
            date = "20.06.2026",
            imageUrl = "",
            isActive = true,
            presentations = listOf(

                Presentation(
                    id = "1",
                    title = "Opening Keynote",
                    speaker = "Max Mustermann",
                    time = "10:00",
                    imageUrl = "",
                    description = "Willkommen zum Event."
                ),

                Presentation(
                    id = "2",
                    title = "Android Workshop",
                    speaker = "Anna Schmidt",
                    time = "11:30",
                    imageUrl = "",
                    description = "Jetpack Compose Grundlagen."
                )
            )
        )
    )

    fun getActiveEvent(): Event? {
        return events.find { it.isActive }
    }

    fun getEventById(id: String): Event? {
        return events.find { it.id == id }
    }

    fun getPresentations(eventId: String): List<Presentation> {
        return getEventById(eventId)?.presentations ?: emptyList()
    }
}