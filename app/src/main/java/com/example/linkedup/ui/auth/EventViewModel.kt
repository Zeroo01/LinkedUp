package com.example.linkedup.ui.auth

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.linkedup.repository.EventRepository
import com.example.linkedup.objects.Event
import com.example.linkedup.objects.Presentation

class EventViewModel : ViewModel() {

    var activeEvent by mutableStateOf<Event?>(null)
        private set

    init {
        load()
    }

    private fun load() {
        activeEvent = EventRepository.getActiveEvent()
    }

    fun onCreateEvent() {}
    fun onParticipants() {}
    fun onPresentationMode() {}
    fun onEventClick(event: Event) {}
}