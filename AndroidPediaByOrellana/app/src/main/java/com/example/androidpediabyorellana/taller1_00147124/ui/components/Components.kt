package com.example.androidpediabyorellana.taller1_00147124.ui.components

import android.widget.Button
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidpediabyorellana.taller1_00147124.model.Question

@Composable
fun QuestionText(pregunta: Question){
    Text(text = pregunta.question)
}

@Composable
fun OptionButton(
    texto: String,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        onClick = onClick,

        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(texto)
    }
}