package com.example.androidpediabyorellana.taller1_00147124

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val funFact: String
)