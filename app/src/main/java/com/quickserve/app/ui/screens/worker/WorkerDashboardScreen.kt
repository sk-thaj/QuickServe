package com.quickserve.app.ui.screens.worker

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quickserve.app.ui.navigation.Screen
import androidx.compose.ui.res.stringResource
import com.quickserve.app.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkerDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.dashboard)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { WorkerBottomNavigationDashboard(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardCard(title = stringResource(R.string.completed_jobs), value = "0", color = MaterialTheme.colorScheme.primary)
            DashboardCard(title = stringResource(R.string.pending_jobs), value = "0", color = MaterialTheme.colorScheme.secondary)
            DashboardCard(title = stringResource(R.string.total_earnings), value = "\u20B90", color = androidx.compose.ui.graphics.Color(0xFF4CAF50))
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(text = title, color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, color = MaterialTheme.colorScheme.onPrimary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun WorkerBottomNavigationDashboard(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(stringResource(R.string.home)) },
            selected = false,
            onClick = { navController.navigate(Screen.WorkerHome.route) {
                popUpTo(Screen.WorkerHome.route) { inclusive = true }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Dashboard") },
            label = { Text(stringResource(R.string.dashboard)) },
            selected = true,
            onClick = { navController.navigate(Screen.WorkerDashboard.route) {
                popUpTo(Screen.WorkerHome.route) { inclusive = false }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text(stringResource(R.string.profile)) },
            selected = false,
            onClick = { navController.navigate(Screen.WorkerProfile.route) {
                popUpTo(Screen.WorkerHome.route) { inclusive = false }
            } }
        )
    }
}
