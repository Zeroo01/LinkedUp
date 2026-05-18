package com.example.linkedup.repository

import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation

object EventRepository {

    val events = listOf(

        Event(
            id = "1",
            title = "Tech Career Expo Berlin",
            description = "Das größte Recruiting Event für Tech Talente.",
            imageUrl = "",
            isActive = true,

            presentations = listOf(

                Presentation(
                    id = "1",
                    title = "Opening Keynote",
                    speaker = "Anna Schmidt",
                    time = "10:00",
                    imageUrl = "",
                    description = "Die Zukunft von AI Recruiting."
                ),

                Presentation(
                    id = "2",
                    title = "Google Workshop",
                    speaker = "Max Weber",
                    time = "11:00",
                    imageUrl = "",
                    description = "Hands-on Android Workshop."
                ),

                Presentation(
                    id = "3",
                    title = "Startup Pitches",
                    speaker = "Various Founders",
                    time = "13:00",
                    imageUrl = "",
                    description = "Die spannendsten Berliner Startups."
                )
            )
        )
    )

    fun getActiveEvent(): Event? {
        return events.find { it.isActive }
    }
}