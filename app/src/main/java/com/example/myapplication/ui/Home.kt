package com.example.myapplication.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.CodeProjectViews

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
//        Add in a text box
        TextboxWithSymbols()
        ClickableSection(navController = navController)
    }
}

@Composable
fun TextboxWithSymbols() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = {
                Column {
                    Text(
                        text = "Healthcare at the click of a button",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Designed to provide users with updated " +
                                "health info in a way that is simple and " +
                                "easy-to-use. ",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Includes the following:",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "* Easy to use Search Engine\n" +
                                "* Map featuring all healthcare clinics on location\n" +
                                "* Directory with all necessary information\n",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            },
            
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun ClickableSection(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        CodeProjectViews.values().forEach { page ->
            if (page.route !== "Home") {
                ClickableItem(
                    text = page.route,
                    description = page.description,
                    onClick = {
                        navController.navigate(page.route)
                    }

                )
            }
        }
        //     description = "Information regarding Healthcare Q&A plus which" +
        //         "health care type best suits your needs.",
        //     onClick = {
        //         navController.navigate("Directory")
        //     }
        // )
    }
}
@Composable
fun ClickableItem(text: String, description: String, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column{
            Text(text = text)
            Text(text = description, style = MaterialTheme.typography.bodySmall)
        }
    }
}