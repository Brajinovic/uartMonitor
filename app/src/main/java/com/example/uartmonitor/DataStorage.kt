package com.example.uartmonitor

import androidx.lifecycle.ViewModel
import java.util.Date

class DataStorage: ViewModel() {

    private var dates = ArrayList<Date>()
    private var temperatures = DoubleArray(101)
    private var moistures = DoubleArray(101)
    private var humidities = DoubleArray(101)

    private var pump: Int = 0
    private var fan: Int = 0
    private var heater: Int = 0

    private var consoleLog = ArrayList<String>()

    fun GetPump(): Int {
        return pump
    }
    fun SetPump(value: Int){
        pump = if (value >= 0) value % 101 else 101 + value
    }
    fun GetFan(): Int{
        return fan
    }
    fun SetFan(value: Int){
        fan = if (value >= 0) value % 101 else 101 + value
    }
    fun GetHeater(): Int{
        return heater
    }
    fun SetHeater(value: Int){
        heater = if (value >= 0) value % 101 else 101 + value
    }

    fun AddDate(date: Date){
        dates.add(date)
    }
    fun GetDates(): ArrayList<Date>{
        return dates
    }
    fun SetDates(dates: ArrayList<Date>){
        this.dates = dates
    }

    fun AddTemperature(temperature: Double, index: Int){
        temperatures[index] = temperature
    }
    fun GetTemperatures(): DoubleArray{
        return temperatures
    }
    fun SetTemperatures(temperatures: DoubleArray){
        this.temperatures = temperatures
    }

    fun AddMoisture(moisture: Double, index: Int){
        moistures[index] = moisture
    }
    fun  GetMoistures(): DoubleArray{
        return moistures
    }
    fun SetMoistures(moistures: DoubleArray){
        this.moistures = moistures
    }
    fun AddHumidity(humidity: Double, index:Int){
        humidities[index] = humidity
    }
    fun GetHumidities(): DoubleArray{
        return humidities
    }
    fun SetHumidities(humidities: DoubleArray){
        this.humidities = humidities
    }

    fun GetConsoleLog(): ArrayList<String>{
        return consoleLog
    }
    fun AddConsoleLog(message: String){
        consoleLog.add(message)
    }
}