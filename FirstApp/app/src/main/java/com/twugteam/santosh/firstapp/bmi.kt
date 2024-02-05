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
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
@Preview(showBackground = true, widthDp = 340, heightDp = 640)
fun BmiCalculatorPreview() {
    val navController = rememberNavController()
    BmiCalculator(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.White,
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "BMI",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        }
    ) { myPadding ->

        val majesticPurple = Color(153, 51, 255)

        var weightInput by remember { mutableFloatStateOf(0f) }
        var heightInput by remember { mutableFloatStateOf(0f) }

        var displayAlertBox by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bmi2),
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
                    Spacer(modifier = Modifier.height(16.dp))
                    // Text field for weight
                    TextField(
                        value = weightInput.toString(),
                        onValueChange = {
//                            weightInput = it.toFloatOrNull() ?: 0f
                                newValue ->
                            weightInput = newValue.toFloatOrNull()!!
                        },
                        label = {
                            Text(
                                text = "Enter your weight in kg",
                                fontSize = 18.sp
                            )
                        },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Text field for height
                    TextField(
                        value = heightInput.toString(),
                        onValueChange = {
//                            heightInput = it.toFloatOrNull() ?: 0f
                                newValue ->
                            heightInput = newValue.toFloatOrNull()!!
                        },
                        label = {
                            Text(
                                text = "Enter your height in meter",
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
                            colors = ButtonDefaults.buttonColors(majesticPurple)
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
                            onClick = { displayAlertBox = true },

                            colors = ButtonDefaults.buttonColors(majesticPurple)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Submit"
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "Submit",
                                    fontSize = 19.sp,
                                    color = Color.White
                                )
                            }
                        }

                    }

                    if (displayAlertBox) {
                        AlertDialog(
                            onDismissRequest = { displayAlertBox = false },

                            confirmButton = {
                                Button(onClick = { displayAlertBox = false }) {
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
                                    text = "BMi :",
                                    fontWeight = FontWeight.Bold,
                                )
                            },

                            text = {
                                Text("The BMI is: ${calculateBMI(weightInput, heightInput)}")
                            })


                    }
                }
            }
        }
    }


}

fun calculateBMI(weight: Float?, height: Float?): Float? {
    if (weight == null || height == null || weight <= 0 || height <= 0) {
        return null
    }
    val heightSquared = height * height
    return weight / heightSquared
}


//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun BmiValue(navController: NavController, bmi: Float?) {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    colors = topAppBarColors(
//                        containerColor = Color.Unspecified,
//                        titleContentColor = Color.White,
//                    ),
//                    title = {
//                        Text(
//                            text = "BMI",
//                            modifier = Modifier.padding(130.dp),
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 31.sp
//                        )
//                    }
//                )
//            }
//        ) { myPadding ->
//
//            val majesticPurple = Color(153, 51, 255)
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.bmi2),
//                    contentDescription = "Background image",
//                    modifier = Modifier
//                        .size(360.dp, 640.dp)
//                        .clip(RoundedCornerShape(45.dp)),
//                    contentScale = ContentScale.FillHeight,
//                )
//                Box(
//                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//                ) {
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxHeight()
//                            .padding(myPadding),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = if (bmi != null) "Your BMI is ${"%.2f".format(bmi)}" else "Invalid Input",
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight.Thin,
//                            color = Color.Blue,
//                            modifier = Modifier
//                                .background(Color.Yellow)
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        // back to home screen button
//                        Button(
//                            onClick = {
//                                navController.navigate(Destinations.BmiScreen)
//                            },
//                            colors = ButtonDefaults.buttonColors(majesticPurple)
//                        ) {
//
//                            Icon(
//                                imageVector = Icons.Default.ArrowBack,
//                                contentDescription = "back to bmi screen"
//                            )
//                            Spacer(modifier = Modifier.padding(2.dp))
//                            Text(
//                                text = "Back",
//                                fontSize = 19.sp,
//                                color = Color.White
//                            )
//
//                        }
//                    }
//                }
//            }
//        }
//    }









