package com.twugteam.santosh.firstapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

//@PreviewParameter()
@Composable
fun MyCalculator(navController: NavController) {
    val majesticPurple = Color(153, 51, 255) // RGB values for a majestic purple

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
                            text = "FitWallet",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        }
    ) { myPadding ->

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img1),
                contentDescription = "Background image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(45.dp)),
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(myPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // bmi screen
                Button(
                    onClick = {
                        navController.navigate(NavRoute.BMIScreen.route)
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(majesticPurple)
                ) {
                    Text(
                        text = "BMI Calculator",
                        fontSize = 19.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                // Tax Screen
                Button(
                    onClick = {
                        navController.navigate(NavRoute.TaxCalculator.route)
                    },
                    colors = ButtonDefaults.buttonColors(majesticPurple)
                ) {
                    Text(
                        text = "Tax Calculator",
                        fontSize = 19.sp
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                // Number To Words screen
                Button(
                    onClick = {
                        navController.navigate(NavRoute.Num2WordsScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(majesticPurple),
                    modifier = Modifier.shadow(elevation = 8.dp)
                ) {
                    Text(
                        text = "Number2Word",
                        fontSize = 19.sp
                    )
                }
            }

        }
    }
}


