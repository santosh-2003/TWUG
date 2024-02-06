package com.twugteam.santosh.firstapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class NavRoute(val route: String) {
    data object MainScreen : NavRoute("mainScreen")
    data object BMIScreen : NavRoute("bmiScreen")
    data object Num2WordsScreen : NavRoute("Num2Words")
    data object TaxCalculator: NavRoute("calculateTax")
}



@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun AppNav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.MainScreen.route) {
        composable(NavRoute.BMIScreen.route) { BmiCalculator(navController) }
        composable(NavRoute.Num2WordsScreen.route) { NumberToWords(navController) }
        composable(NavRoute.TaxCalculator.route) { TaxCalculator(navController) }
        composable(NavRoute.MainScreen.route) { MyCalculator(navController) }
    }
}












































//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//
//object Destinations {
//    const val BmiScreen = "bmiScreen"
//    const val TaxCalculate = "taxCalculate"
//    const val Number2Words = "number2words"
//    const val MainScreen = "mainScreen"
//    const val BmiCalculate = "bmiCalculate"
//    const val TaxDisplay = "taxValue"
//    const val intoWords = "IntoWords"
//}
//
//@Preview(showBackground = true, widthDp = 340, heightDp = 640)
//@Composable
//fun AppNavHost() {
//
//    val navController = rememberNavController()
//
//    // Declare weightInput and heightInput as mutable state variables
//    var weightInput by remember { mutableStateOf("") }
//    var heightInput by remember { mutableStateOf("") }
//    NavHost(navController = navController, startDestination = Destinations.MainScreen) {
//
//        composable(route = Destinations.MainScreen) {
//            MyCalculator(navController)
//        }
//
//        composable(route = Destinations.BmiScreen) {
//            BmiCalculator(navController)
//        }
//
//        composable(route = Destinations.BmiCalculate) {
//            BmiValue(navController, bmi)
//        }
//
//        composable(route = Destinations.TaxCalculate) {
//            TaxCalculator(navController)
//        }
//
//        composable(route = Destinations.TaxDisplay) {
//            TaxValue(navController = navController, bmi = null)
//        }
//
//        composable(Destinations.Number2Words) {
//            NumberToWords(navController)
//        }
//
//        composable(route = Destinations.intoWords){
//        IntoWords(navController = navController, bmi = null)
//        }
//
//
//
//
//
//
//    }
//
//}
//
//
//
