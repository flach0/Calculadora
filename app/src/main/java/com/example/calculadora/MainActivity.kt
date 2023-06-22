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
                val caracteres = charArrayOf('+', '-', '*', '/')

                fun tieneDecimales(numero: Double): Boolean {
                    return numero % 1 != 0.0
                }

                fun resultado (numero1: Double, numero2: Double, simbol: Char): Double? {

                    var operator = when (simbol) {
                        '+' -> numero1 + numero2
                        '-' -> numero1 - numero2
                        '*' -> numero1 * numero2
                        '/' -> numero1 / numero2
                        else -> {
                            println("chupala justin")
                            null
                        }

                    }
                    return operator
                }



                fun calculadora(){
                    val spliter = entradas
                    val splitter1 = spliter.split(*delimitadores)

                    if (splitter1.size < 2)
                        return;

                    if (splitter1[0].isNullOrEmpty() || splitter1[1].isNullOrEmpty()) {
                        return;
                    }

                    val parte1 = splitter1[0].toDouble()
                    val parte2 = splitter1[1].toDouble()

                    for (symboloEnCalculadora in entradas){
                        entradas = when (symboloEnCalculadora) {
                            '+', '-', '*' -> {
                                resultado(parte1, parte2, symboloEnCalculadora).toString()
                            }
                            '/' -> {
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
                                onClick = { if (entradas.isNotEmpty()) entradas += "*" }
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
                                onClick = { if (entradas.isNotEmpty()) entradas += "/" }
                            ) {
                                Text(text = "/")
                            }
                        }
                        Row(modifier = Modifier.background(color = Color.LightGray)) {
                            Button(
                                onClick = {if (entradas.isNotEmpty()) entradas += "+" }
                            ) {
                                Text(text = "+")
                            }
                            Button(
                                onClick = {if (entradas.isNotEmpty()) entradas += "0"}
                            ) {
                                Text(text = "0")
                            }
                            Button(
                                onClick = {if (entradas.isNotEmpty()) entradas += "-"}
                            ) {
                                Text(text = "-")
                            }
                            Button(
                                onClick = {

                                    if (entradas != ""){
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


