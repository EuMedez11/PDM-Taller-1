package com.example.androidpediabyorellana.taller1_00147124.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidpediabyorellana.taller1_00147124.data.quizQuestions
import com.example.androidpediabyorellana.taller1_00147124.ui.components.OptionButton
import com.example.androidpediabyorellana.taller1_00147124.ui.components.QuestionText
import com.example.androidpediabyorellana.ui.theme.AndroidPediaByOrellanaTheme


@Composable
fun AppEstudio(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var preguntaActual by remember { mutableIntStateOf(0) }
    var puntaje by remember { mutableIntStateOf(0) }
    var respondido by remember { mutableStateOf(false) }
    var respuestaSeleccionada by remember { mutableIntStateOf(-1) }

    when (currentStep) {

        1 -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("AndroidPedia")
                Text("¿Cuánto sabes de Android?")
                Text("Emily Eunice Orellana Mendez - 00147124")

                Button(onClick = { currentStep = 2 }) {
                    Text("Comenzar Quiz")
                }
            }
        }

        2 -> {
            val pregunta = quizQuestions[preguntaActual]

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Pregunta ${preguntaActual + 1} de 3")
                Text("Puntaje: $puntaje / 3")

                QuestionText(pregunta)

                pregunta.options.forEachIndexed { index, opcion ->

                    val buttonColor = when {
                        respondido && opcion == pregunta.correctAnswer -> Color.Green
                        respondido && index == respuestaSeleccionada && opcion != pregunta.correctAnswer -> Color.Red
                        else -> Color.LightGray
                    }

                    OptionButton(
                        texto = opcion,
                        buttonColor = buttonColor,
                        onClick = {
                            if (!respondido) {
                                respuestaSeleccionada = index
                                respondido = true

                                if (opcion == pregunta.correctAnswer) {
                                    puntaje++
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (respondido) {
                    Text(
                        text = pregunta.funFact,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )

                    Button(onClick = {
                        if (preguntaActual < 2) {
                            preguntaActual++
                            respondido = false
                            respuestaSeleccionada = -1
                        } else {
                            currentStep = 3
                        }
                    }) {
                        Text("Siguiente")
                    }
                }
            }
        }

        3 -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Obtuviste $puntaje de 3")

                val mensaje = when (puntaje) {
                    3 -> "Excelente"
                    2 -> "Muy bien"
                    else -> "Sigue practicando"
                }

                Text(mensaje)

                Button(onClick = {
                    currentStep = 1
                    preguntaActual = 0
                    puntaje = 0
                    respondido = false
                    respuestaSeleccionada = -1
                }) {
                    Text("Reiniciar Quiz")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPregunta() {
    AndroidPediaByOrellanaTheme {
        AppEstudio()
    }
}


