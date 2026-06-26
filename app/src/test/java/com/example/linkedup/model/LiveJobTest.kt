package com.example.linkedup.model

import org.junit.Assert.assertEquals
import org.junit.Test


class LiveJobTest {



    @Test
    fun `live job has live status`() {


        val job =
            LiveJob(

                title = "Android Entwickler",

                recruiterId = "rec123",

                status = "LIVE"

            )


        assertEquals(
            "LIVE",
            job.status
        )

    }

}