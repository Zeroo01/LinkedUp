package com.example.linkedup.sensor

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HandRaiseLogic {


    fun isHandRaised(
        accelerationY: Float
    ): Boolean {


        return accelerationY > 12

    }

}

class HandRaiseLogicTest {


    private val logic =
        HandRaiseLogic()



    @Test
    fun `hand raise detected when acceleration is high`() {


        val result =
            logic.isHandRaised(15f)



        assertTrue(result)

    }




    @Test
    fun `no hand raise when acceleration is low`() {


        val result =
            logic.isHandRaised(5f)



        assertFalse(result)

    }


}