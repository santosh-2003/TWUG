package com.twugteam.santosh.firstapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxCalculator(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.DarkGray,
                ),
                title = {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)) {
                        Text(
                            text = "TAX",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        }
    ) { myPadding ->

        val darkBrown = Color(101, 67, 33)
        var salaryInput by remember { mutableStateOf(0.0) }
        var ShowTaxDialog by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.tax1),
                contentDescription = "Background image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(45.dp)),
                contentScale = ContentScale.FillHeight,
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(myPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // Text field for tax
                    TextField(
                        value = salaryInput.toString(),
                        onValueChange = {
                            salaryInput = it.toDoubleOrNull() ?: 0.0
                        },
                        label = {
                            Text(
                                text = "Enter your salary",
                                fontSize = 18.sp
                            )
                        },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Row containing buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Home button
                        Button(
                            onClick = {
                                navController.navigate(NavRoute.MainScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(darkBrown)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "home"
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "Home",
                                    fontSize = 19.sp,
                                    color = Color.White,
                                    modifier = Modifier.clickable {
                                        navController.navigate(NavRoute.MainScreen.route)
                                    }
                                )
                            }
                        }

                        // Submit button
                        Button(
                            onClick = {
                                ShowTaxDialog = true
                            },
                            colors = ButtonDefaults.buttonColors(darkBrown)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "TaxCalc"
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "TaxCalc",
                                    fontSize = 19.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    if (ShowTaxDialog) {
                        AlertDialog(
                            onDismissRequest = { ShowTaxDialog = false },

                            confirmButton = {
                                Button(onClick = { ShowTaxDialog = false }) {
                                    Text("OK")
                                }
                            },

                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "info"
                                )
                            },

                            title = {
                                Text(
                                    text = "TAX :",
                                    fontWeight = FontWeight.Bold,
                                )
                            },

                            text = {
                                Text("The Tax is: ${calculateTax(salaryInput)}")
                            })


                    }
                }
            }
        }
    }
}


fun calculateTax(income: Double): Double {
    // Define tax brackets and rates
    val brackets = doubleArrayOf(400000.0, 700000.0, 1000000.0, 2000000.0)
    val rates = doubleArrayOf(0.01, 0.10, 0.20, 0.30, 0.36)

    // Initialize variables
    var remainingIncome = income
    var totalTax = 0.0

    // Iterate through tax brackets
    for (i in 0 until brackets.size) {
        if (remainingIncome <= 0) {
            break // No remaining income, stop calculation
        }
        val taxableAmount = if (remainingIncome > brackets[i]) brackets[i] else remainingIncome
        val tax = taxableAmount * rates[i]
        totalTax += tax
        remainingIncome -= taxableAmount
    }

    // Return total tax amount
    return totalTax
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TaxValue(navController: NavController, bmi: Float?) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = topAppBarColors(
//                    containerColor = Color.Unspecified,
//                    titleContentColor = Color.White,
//                ),
//                title = {
//                    Text(
//                        text = "TAX",
//                        modifier = Modifier.padding(130.dp),
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 31.sp
//                    )
//                }
//            )
//        }
//    ) { myPadding ->
//
//        val majesticPurple = Color(153, 51, 255)
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.tax1),
//                contentDescription = "Background image",
//                modifier = Modifier
//                    .size(360.dp, 640.dp)
//                    .clip(RoundedCornerShape(45.dp)),
//                contentScale = ContentScale.FillHeight,
//            )
//            Box(
//                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//            ) {
//                val darkBrown = Color(101, 67, 33)
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .padding(myPadding),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Tax is 100% Free for you 😘",
//                        fontSize = 19.sp,
//                        modifier = Modifier
//                            .padding(10.dp)
//                            .background(Color.Yellow),
//                        fontWeight = FontWeight.Thin
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // back to home screen button
//                    Button(
//                        onClick = {
//                            navController.navigate(Destinations.TaxCalculate)
//                        },
//                        colors = ButtonDefaults.buttonColors(darkBrown)
//                    ) {
//
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "back to tax screen"
//                        )
//                        Spacer(modifier = Modifier.padding(2.dp))
//                        Text(
//                            text = "Back",
//                            fontSize = 19.sp,
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//        }
//    }
//}


//fun calculateBMI(weight: Float?, height: Float?): Float? {
//    if (weight == null || height == null || weight <= 0 || height <= 0) {
//        return null
//    }
//    val heightSquared = height * height
//    return weight / heightSquared
//}










