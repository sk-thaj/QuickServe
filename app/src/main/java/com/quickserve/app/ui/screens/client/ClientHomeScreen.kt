package com.quickserve.app.ui.screens.client

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quickserve.app.ui.navigation.Screen
import androidx.compose.ui.res.stringResource
import com.quickserve.app.R
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale

data class WorkerProfile(val id: Int, val name: String, val service: String, val rating: Double, val reviewsCount: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientHomeScreen(navController: NavController) {
    val workers = listOf(
        WorkerProfile(1, "Ramesh Kumar", "Electrician", 4.5, 12),
        WorkerProfile(2, "Suresh Raj", "Plumber", 4.8, 34),
        WorkerProfile(3, "Babu Rao", "Mechanic", 4.2, 8),
        WorkerProfile(4, "Krishna Veni", "Cleaner", 5.0, 42),
        WorkerProfile(5, "Ram Charan", "Driver", 4.6, 21)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.available_workers)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { ClientBottomNavigation(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(workers) { worker ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { /* TODO: Go to worker details */ },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            coil.compose.AsyncImage(
                                model = "https://i.pravatar.cc/150?u=${worker.id}",
                                contentDescription = "Worker Avatar",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = worker.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(text = stringResource(R.string.service) + ": ${worker.service}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = "Rating", tint = androidx.compose.ui.graphics.Color(0xFFFFC107))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "${worker.rating} (${worker.reviewsCount} " + stringResource(R.string.review) + "s)", fontWeight = FontWeight.Medium)
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { 
                                // Mock booking action
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(stringResource(R.string.book_worker))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClientBottomNavigation(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(stringResource(R.string.home)) },
            selected = true,
            onClick = { navController.navigate(Screen.ClientHome.route) {
                popUpTo(Screen.ClientHome.route) { inclusive = true }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Bookings") },
            label = { Text(stringResource(R.string.bookings)) },
            selected = false,
            onClick = { navController.navigate(Screen.ClientBookings.route) {
                popUpTo(Screen.ClientHome.route) { inclusive = false }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text(stringResource(R.string.profile)) },
            selected = false,
            onClick = { navController.navigate(Screen.ClientProfile.route) {
                popUpTo(Screen.ClientHome.route) { inclusive = false }
            } }
        )
    }
}
