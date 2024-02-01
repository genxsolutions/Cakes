package com.genxsol.cakesapp.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.genxsol.cakesapp.R
import com.genxsol.cakesapp.ui.screens.CakesScreen
import com.genxsol.cakesapp.ui.screens.ShowPopup

@Composable
fun CakeNavHost() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CakesTopBar()
        }
    ) {
        CakeNavHost(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CakesTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.app_name)
            )
        }
    )
}

@Composable
private fun CakeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.ListScreen.route
        ) {
            CakesScreen { description ->
                navController.navigate("${Screen.PopupScreen.route}/$description")
            }
        }
        composable(
            route = "${Screen.PopupScreen.route}/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            ShowPopup(navController, text)
        }
    }
}



