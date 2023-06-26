package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme
import java.lang.ArithmeticException


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {

                var entradas by remember {
                    mutableStateOf("")
                }

                // esto es un comentario de prueba.
                val delimitadores = arrayOf("+", "-", "*", "/")

                fun tieneDecimales(numero: Double): Boolean {
                    return numero % 1 != 0.0
                }

                fun resultado(numero1: Double, numero2: Double, simbol: Char): Double? {

                    return try {
                        when (simbol) {
                            '+' -> numero1 + numero2
                            '-' -> numero1 - numero2
                            '*' -> numero1 * numero2
                            '/' -> numero1 / numero2
                            else -> {
                                println("chupala justin")
                                null
                            }
                        }
                    } catch (e: ArithmeticException) {
                        null
                    }
                }

                fun procesos(negativo: Boolean = false) {

                    val splitter1 = entradas.split(*delimitadores)

                    if (splitter1.size < 2)
                        return;


                    if (splitter1[0].isNullOrEmpty() || splitter1[1].isNullOrEmpty()) {
                        return;
                    }

                    var parte1 = splitter1[0].toDouble()
                    val parte2 = splitter1[1].toDouble()

                    if (negativo) parte1 = -parte1

                    for (symboloEnCalculadora in entradas) {

                        entradas = when (symboloEnCalculadora) {
                            '+', '-', '*' -> {
                                resultado(parte1, parte2, symboloEnCalculadora).toString()
                            }

                            '/' -> {
                                if (parte2 == 0.0) {
                                    entradas = "Chupala no se puede dividir entre 0"
                                    return;
                                }
                                val res = resultado(parte1, parte2, symboloEnCalculadora)
                                if (res != null) {
                                    if (tieneDecimales(res)) {
                                        res.toString().take(4).toDouble().toString()

                                    } else {
                                        res.toString()
                                    }
                                } else {
                                    return;
                                }
                            }

                            else -> entradas
                        }
                    }
                }

                fun checkOperators(entradas: String, pressedOperator: Char): String {
                    if (entradas.isEmpty() && pressedOperator != '-') {
                        return "";
                    } else if (entradas.isNotEmpty() && entradas.last() === '-' && entradas.length === 1) {
                        return entradas;
                    } else if (entradas.isNotEmpty() && entradas.last() !in setOf('/', '+', '-', '*')) {
                        return entradas + pressedOperator;
                    } else {
                        return entradas.dropLast(1) + pressedOperator;
                    }
                }

                fun calculadora() {

                    if (entradas.startsWith('-')) {
                        entradas = entradas.substring(1)
                        procesos(true)

                    } else {
                        procesos()
                    }
                }

                Column(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.background(color = Color.Gray),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Asi aprendes a calcular trolazo te gusta el trozo",
                            color = Color.Green,
                            modifier = Modifier.padding(13.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.background(color = Color.Red),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Calculadora",
                            color = Color.Blue,
                            modifier = Modifier.padding(13.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.background(color = Color.Gray),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Suma y resta")
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(
                            modifier = Modifier.padding(80.dp, 10.dp)
                        ) {
                            Text(
                                text = "$entradas",
                                fontSize = 20.sp,
                                modifier = Modifier.background(color = Color.White)
                            )

                        }
                        Row(modifier = Modifier.background(color = Color.LightGray)) {
                            Button(
                                onClick = {

                                    entradas += "1"

                                }
                            ) {
                                Text(text = "1")
                            }
                            Button(
                                onClick = {

                                    entradas += "2"
                                }
                            ) {
                                Text(text = "2")
                            }
                            Button(
                                onClick = { entradas += "3" }
                            ) {
                                Text(text = "3")
                            }
                            Button(
                                onClick = { entradas = "" }
                            ) {
                                Text(text = "C")
                            }
                        }
                        Row(modifier = Modifier.background(color = Color.LightGray)) {
                            Button(
                                onClick = { entradas += "4" }
                            ) {
                                Text(text = "4")
                            }
                            Button(
                                onClick = { entradas += "5" }
                            ) {
                                Text(text = "5")
                            }
                            Button(
                                onClick = { entradas += "6" }
                            ) {
                                Text(text = "6")
                            }
                            Button(
                                onClick = {
                                    entradas = checkOperators(entradas, '*');
                                }
                            ) {
                                Text(text = "*")
                            }
                        }
                        Row(modifier = Modifier.background(color = Color.LightGray)) {
                            Button(
                                onClick = { entradas += "7" }
                            ) {
                                Text(text = "7")
                            }
                            Button(
                                onClick = { entradas += "8" }
                            ) {
                                Text(text = "8")
                            }
                            Button(
                                onClick = { entradas += "9" }
                            ) {
                                Text(text = "9")
                            }
                            Button(
                                onClick = {
                                    entradas = checkOperators(entradas, '/');
                                }
                            ) {
                                Text(text = "/")
                            }
                        }
                        Row(modifier = Modifier.background(color = Color.LightGray)) {
                            Button(
                                onClick = {
                                    entradas = checkOperators(entradas, '+');
                                }
                            ) {
                                Text(text = "+")
                            }
                            Button(
                                onClick = { if (entradas.isNotEmpty()) entradas += "0" }
                            ) {
                                Text(text = "0")
                            }
                            Button(
                                onClick = {
                                    entradas = checkOperators(entradas, '-');
                                }
                            ) {
                                Text(text = "-")
                            }
                            Button(
                                onClick = {

                                    if (entradas != "") {
                                        calculadora()
                                    }

                                }
                            ) {
                                Text(text = "=")
                            }
                        }
                    }
                }
            }
        }
    }
}


