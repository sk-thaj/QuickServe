package com.quickserve.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quickserve.app.ui.navigation.Screen
import com.quickserve.app.ui.screens.auth.LoginScreen
import com.quickserve.app.ui.screens.auth.RoleSelectionScreen
import com.quickserve.app.ui.screens.auth.WorkerRegistrationScreen
import com.quickserve.app.ui.screens.auth.ClientRegistrationScreen
import com.quickserve.app.ui.screens.worker.WorkerHomeScreen
import com.quickserve.app.ui.screens.worker.WorkerDashboardScreen
import com.quickserve.app.ui.screens.worker.WorkerProfileScreen
import com.quickserve.app.ui.screens.client.ClientHomeScreen
import com.quickserve.app.ui.screens.client.ClientBookingsScreen
import com.quickserve.app.ui.screens.client.ClientProfileScreen
import com.quickserve.app.ui.theme.QuickServeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickServeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Login.route) {
                        composable(Screen.Login.route) { LoginScreen(navController) }
                        composable(Screen.RoleSelection.route) { RoleSelectionScreen(navController) }
                        composable(Screen.WorkerRegistration.route) { WorkerRegistrationScreen(navController) }
                        composable(Screen.ClientRegistration.route) { ClientRegistrationScreen(navController) }
                        composable(Screen.WorkerHome.route) { WorkerHomeScreen(navController) }
                        composable(Screen.WorkerDashboard.route) { WorkerDashboardScreen(navController) }
                        composable(Screen.WorkerProfile.route) { WorkerProfileScreen(navController) }
                        composable(Screen.ClientHome.route) { ClientHomeScreen(navController) }
                        composable(Screen.ClientBookings.route) { ClientBookingsScreen(navController) }
                        composable(Screen.ClientProfile.route) { ClientProfileScreen(navController) }
                    }
                }
            }
        }
    }
}
