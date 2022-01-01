package com.laurenyew.agilityfittodayapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.laurenyew.agilityfittodayapp.ui.navigation.NavigationRoutes

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the Agility Fit Today App!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "This sample app features fitness workout creations that demo Jetpack libraries, Kotlin Coroutines & Flows, and other Android tech / architecture.",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(NavigationRoutes.StartWorkoutSelectWorkout.route) {
                popUpTo = navController.graph.startDestinationId
                launchSingleTop = true
            }
        }) {
            Text(
                text = "Start a Workout",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            Toast.makeText(context, "Create a Workout", Toast.LENGTH_LONG).show()
        }) {
            Text(
                text = "Create a Workout",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            Toast.makeText(context, "Old Workouts", Toast.LENGTH_LONG).show()
        }) {
            Text(
                text = "Old Workouts",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}