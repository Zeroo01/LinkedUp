package com.example.linkedup.objects
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val timeline: List<EventItem>
)

data class EventItem(
    val time: String,
    val title: String,
    val type: String // z.B. "Talk", "Workshop", "Pitch"
)



