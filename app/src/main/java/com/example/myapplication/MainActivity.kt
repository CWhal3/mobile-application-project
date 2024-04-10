package com.example.myapplication

import android.os.Bundle
import androidx.annotation.StringRes
// import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable


import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.myapplication.ui.HomeScreen
import com.example.myapplication.ui.NewScreen
import com.example.myapplication.ui.theme.AppTheme

enum class CodeProjectViews(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Other(title = R.string.app_name),
    Directory(title = R.string.app_name),
}

@Composable
fun AppBarTop(
    currentScreen: CodeProjectViews,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back", // This was `stringResource(R.string.back_button)`
                    )
                }
            }
        }
    )

}

@Composable
fun AppBarBottom(
    currentScreen: CodeProjectViews,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = {
            }) {
                Icon(Icons.Filled.LocationOn, contentDescription = "Description")
            }
            IconButton(onClick = {
                navController.popBackStack(CodeProjectViews.Home.name, inclusive = false)
            }) {
                Icon(Icons.Filled.Home, contentDescription = "Description")
            }
            IconButton(onClick = {
                navController.popBackStack(CodeProjectViews.Other.name, inclusive = false)
            }) {
                Icon(Icons.Filled.List, contentDescription = "Description")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Search, contentDescription = "Description")
            }
        }
    )
}

@Composable
fun Application(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = CodeProjectViews.valueOf(
        backStackEntry?.destination?.route ?: CodeProjectViews.Home.name
    )

    Scaffold(
        topBar = {
            AppBarTop(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
        bottomBar = {
            AppBarBottom(
                currentScreen = currentScreen,
                navController = navController,
            )
        }
    ) { innerPadding ->
        // val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CodeProjectViews.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
        ) {
            // This is where the code for rendering the different variants go
            composable(route = CodeProjectViews.Home.name) {
                HomeScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = CodeProjectViews.Other.name) {
                NewScreen(modifier = Modifier.fillMaxHeight())
            }
        }
    }

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Application()
            }
        }
        // setContentView(R.layout.activity_main)
    }
}