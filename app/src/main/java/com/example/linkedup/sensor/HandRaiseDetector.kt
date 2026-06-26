package com.example.linkedup.sensor


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager



class HandRaiseDetector(

    context: Context,

    private val onHandRaised:()->Unit

): SensorEventListener {



    private val sensorManager =
        context.getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager



    private val sensor =
        sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER
        )



    fun start(){

        sensorManager.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

    }



    fun stop(){

        sensorManager.unregisterListener(this)

    }



    override fun onSensorChanged(
        event:SensorEvent?
    ){

        val y =
            event?.values?.get(1)
                ?: return


        if(y > 12){

            onHandRaised()

        }

    }



    override fun onAccuracyChanged(
        sensor:Sensor?,
        accuracy:Int
    ){}

}