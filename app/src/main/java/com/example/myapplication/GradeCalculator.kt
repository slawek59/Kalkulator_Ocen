package com.example.myapplication

import kotlin.ranges.contains

class GradeCalculator {

    fun calculateGrade(pointsAmount: Int?): String{
        return when (pointsAmount) {
            in 0..49 -> "2.0"
            in 50..60 -> "3.0"
            in 61..70 -> "3.5"
            in 71..80 -> "4.0"
            in 81..90 -> "4.5"
            in 91..100 -> "5.0"
            else -> "Nieznana"
        }
    }

    fun isValidScore(pointsAmount: Int?) = pointsAmount != null && pointsAmount in 0..100

}