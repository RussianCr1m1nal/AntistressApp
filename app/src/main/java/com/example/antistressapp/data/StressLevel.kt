package com.example.antistressapp.data

import com.example.antistressapp.data.StressLevel
import java.util.HashMap

object StressLevel {
    private val stressLevelMap: HashMap<Float?, Int?> = object : HashMap<Float?, Int?>() {
        init {
            put(0.0f, 60)
            put(0.5f, 120)
            put(1.0f, 180)
            put(1.5f, 240)
            put(2.0f, 300)
            put(2.5f, 360)
            put(3.0f, 420)
            put(3.5f, 540)
            put(4.0f, 660)
            put(4.5f, 780)
            put(5.0f, 900)
        }
    }

    fun getSecondsFromStressLevel(stressLevel: Float): Int {
        return stressLevelMap[stressLevel]!!
    }
}