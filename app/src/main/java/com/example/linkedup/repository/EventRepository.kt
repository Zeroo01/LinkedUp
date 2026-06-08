package com.example.linkedup.repository

import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation
class EventRepository {

    private val events = listOf(
        Event(
            id = "1",
            title = "Test Event titel",
            description = "filler filler filler description",
            imageUrl = "",
            isActive = true,
            presentations = listOf(
                Presentation(
                    id = "1",
                    title = "TestName 1",
                    speaker = "Test speaker 1",
                    time = "10:00",
                    imageUrl = "",
                    description = "1111 test 1111."
                ),
                Presentation(
                    id = "2",
                    title = "TestName 2",
                    speaker = "Test speaker 2",
                    time = "11:00",
                    imageUrl = "",
                    description = "2222 test 22222"
                ),
                Presentation(
                    id = "3",
                    title = "TestName 3",
                    speaker = "Test speaker 3",
                    time = "13:00",
                    imageUrl = "",
                    description = "3333 test 333333."
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
}