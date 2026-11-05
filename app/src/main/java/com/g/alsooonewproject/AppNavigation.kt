package com.g.alsooonewproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Home


@Serializable
data class TaskDetails(
    val id: String
)


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    println("Launching")
                    navController.navigate(
                        TaskDetails(
                            id = "adsffsd"
                        )
                    )
                }) {
                    Text("Go to Task Details")
                }
            }
        }
        composable<TaskDetails> { backStackEntry ->
            var route = backStackEntry.toRoute<TaskDetails>()
            Column() {
                Text(route.id)
            }
        }
    }
}
