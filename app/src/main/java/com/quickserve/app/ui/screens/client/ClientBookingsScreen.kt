package com.quickserve.app.ui.screens.client

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientBookingsScreen(navController: NavController) {
    var showReviewDialog by remember { mutableStateOf(false) }
    var selectedWorker by remember { mutableStateOf<WorkerProfile?>(null) }
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(5f) }

    val completedBookings = listOf(
        WorkerProfile(1, "Ramesh Kumar", "Electrician", 4.5, 12)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.my_bookings)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { ClientBottomNavigationBookings(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item { 
                Text(stringResource(R.string.completed_bookings), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(completedBookings) { worker ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = worker.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = stringResource(R.string.service) + ": ${worker.service}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { 
                                selectedWorker = worker
                                showReviewDialog = true
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(stringResource(R.string.give_rating_review), color = MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }
            }
        }

        if (showReviewDialog && selectedWorker != null) {
            AlertDialog(
                onDismissRequest = { showReviewDialog = false },
                title = { Text(stringResource(R.string.review) + " ${selectedWorker?.name}") },
                text = {
                    Column {
                        Text("Rating: ${rating.toInt()} Stars")
                        Slider(
                            value = rating,
                            onValueChange = { rating = it },
                            valueRange = 1f..5f,
                            steps = 3
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = reviewText,
                            onValueChange = { reviewText = it },
                            label = { Text(stringResource(R.string.write_your_review)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { 
                            showReviewDialog = false 
                            reviewText = ""
                            rating = 5f
                        }
                    ) {
                        Text(stringResource(R.string.submit))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showReviewDialog = false }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }
    }
}

@Composable
fun ClientBottomNavigationBookings(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(stringResource(R.string.home)) },
            selected = false,
            onClick = { navController.navigate(Screen.ClientHome.route) {
                popUpTo(Screen.ClientHome.route) { inclusive = true }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Bookings") },
            label = { Text(stringResource(R.string.bookings)) },
            selected = true,
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
