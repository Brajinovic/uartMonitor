package com.example.uartmonitor

import retrofit2.Call
import retrofit2.http.GET

interface TemperatureService {

    @GET("feed.json")
    fun getTemperatureList () : Call<DataPacket>
}