package com.example.linkedup.ui.auth

import com.example.linkedup.objects.Presentation
import com.example.linkedup.repository.EventRepository

class EventViewModel(
    private val repo: EventRepository = EventRepository()
) {

    val activeEvent = repo.getActiveEvent()

    fun getPresentations(): List<Presentation> {
        return activeEvent?.presentations ?: emptyList()
    }
}