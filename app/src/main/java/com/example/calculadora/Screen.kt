package com.example.calculadora

sealed class Screen(val route: String){
    object MainScreen : Screen("main_Screen")
    object DetailScreen : Screen("detail_Screen")

}
