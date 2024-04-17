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
import android.graphics.drawable.Icon
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.DirectoryScreen

import com.example.myapplication.ui.HomeScreen
import com.example.myapplication.ui.MapScreen

import com.example.myapplication.ui.SearchScreen
import com.example.myapplication.ui.theme.AppTheme



enum class CodeProjectViews(val route: String, val icon: ImageVector) {
    Home(route = "Home", icon = Icons.Filled.LocationOn),
    Directory(route = "Directory", icon = Icons.Filled.Home),
    Search(route = "Search", icon = Icons.Filled.Search),
    Map(route = "Map", icon = Icons.Filled.List),
}

@Composable
fun AppBarTop(
    currentScreen: CodeProjectViews,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(currentScreen.route) },
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
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        CodeProjectViews.values().forEach { page ->
            NavigationBarItem(
                selected = currentRoute == page.route,
                label = { Text(page.name) },
                onClick = {
                    navController.navigate(page.route)
                },
                icon = {
                   Icon(page.icon, contentDescription = page.name)
                },
            )
        }
    }
}

@Composable
fun Application(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = CodeProjectViews.valueOf(
        backStackEntry?.destination?.route ?: CodeProjectViews.Home.route
    )

    Scaffold(
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
            startDestination = CodeProjectViews.Home.route,
            modifier = Modifier
                .fillMaxSize()

                .padding(innerPadding),
        ) {
            // This is where the code for rendering the different variants go
            composable(route = CodeProjectViews.Home.route) {
                HomeScreen(navController = navController, modifier = Modifier.fillMaxHeight())
            }
            composable(route = CodeProjectViews.Directory.route) {
                DirectoryScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = CodeProjectViews.Search.route) {
                SearchScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = CodeProjectViews.Map.route) {
                MapScreen(modifier = Modifier.fillMaxSize())
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