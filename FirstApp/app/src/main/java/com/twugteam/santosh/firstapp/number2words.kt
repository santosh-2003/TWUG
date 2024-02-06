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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberToWords(navController: NavController) {
    val purple = Color(128, 0, 128)
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.White,
                ),
                title = {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)) {
                        Text(
                            text = "Number2Words",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        }
    ) { myPadding ->

        val purple = Color(128, 0, 128)

        var numberInput by remember { mutableStateOf("") }

        var displayWords by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.num3),
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
                        value = numberInput.toString(),
                        onValueChange = {
                            numberInput = it.toInt().toString()
                        },
                        label = {
                            Text(
                                text = "Enter the number you want",
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
                                displayWords = false
                                navController.navigate(NavRoute.MainScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(purple)
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
                                displayWords = true
//                                navController.navigate(NavRoute.Num2WordsScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(purple)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "TaxCalc"
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "Convert",
                                    fontSize = 19.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    if (displayWords) {
                        AlertDialog(
                            onDismissRequest = { displayWords = false },

                            confirmButton = {
                                Button(onClick = { displayWords = false }) {
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
                                    text = "Number2Words :",
                                    fontWeight = FontWeight.Bold,
                                )
                            },

                            text = {
                                Text("The Number in word is : ${convertNumberToWords(numberInput.toInt())}")
                            })


                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun dispaly() {
    val navController = rememberNavController()
    NumberToWords(navController)

}

fun convertNumberToWords(number: Int): String {
    val onesArray = arrayOf(
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
    )
    val tensArray = arrayOf(
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    )
    val teensArray = arrayOf(
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    )

    return when {
        number < 10 -> onesArray[number]
        number < 20 -> teensArray[number - 10]
        number < 100 -> tensArray[number / 10] + " " + convertNumberToWords(number % 10)
        number < 1000 -> onesArray[number / 100] + " Hundred " + convertNumberToWords(number % 100)
        number < 1000000 -> convertNumberToWords(number / 1000) + " Thousand " + convertNumberToWords(
            number % 1000
        )

        number < 1000000000 -> convertNumberToWords(number / 1000000) + " Million " + convertNumberToWords(
            number % 1000000
        )

        else -> "Number out of range"
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun IntoWords(navController: NavController, bmi: Float?) {
//    val purple = Color(128, 0, 128)
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = topAppBarColors(
//                    containerColor = Color.Unspecified,
//                    titleContentColor = Color.White,
//                ),
//                title = {
//                    Text(
//                        text = "Number2Words",
//                        modifier = Modifier.padding(47.dp),
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 31.sp
//                    )
//                }
//            )
//        }
//    ) { myPadding ->
//
//
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.num3),
//                contentDescription = "Background image",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(45.dp)),
//                contentScale = ContentScale.FillHeight,
//            )
//            Box(
//                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .padding(myPadding),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Better Luck Next time 😉",
//                        fontSize = 29.sp,
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
//                            navController.navigate(Destinations.Number2Words)
//                        },
//                        colors = ButtonDefaults.buttonColors(purple)
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











