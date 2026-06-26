package com.example.linkedup.data

import com.example.linkedup.model.ApplicationData
import org.junit.Assert.assertEquals
import org.junit.Test


class ApplicationRepositoryTest {



    @Test
    fun `application contains correct data`() {


        val application =
            ApplicationData(

                applicantId = "user123",

                jobId = "job456",

                recruiterId = "recruiter789",

                status = "NEW"

            )



        assertEquals(
            "user123",
            application.applicantId
        )


        assertEquals(
            "job456",
            application.jobId
        )


        assertEquals(
            "NEW",
            application.status
        )

    }

}