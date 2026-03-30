package com.quickserve.app.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quickserve.app.ui.navigation.Screen
import androidx.compose.ui.res.stringResource
import com.quickserve.app.R

@Composable
fun LoginScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isOtpSent by remember { mutableStateOf(false) }
    var isOtpVerified by remember { mutableStateOf(false) }
    
    var phoneNumberError by remember { mutableStateOf(false) }
    var otpError by remember { mutableStateOf(false) }
    var showGenericError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "QuickServe",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (showGenericError) {
            Text(
                text = "Please enter all fields",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newValue -> 
                if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                    phoneNumber = newValue
                }
            },
            label = { Text(stringResource(R.string.phone_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            isError = phoneNumberError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isOtpVerified) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("Login As:", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.WorkerHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Worker", color = MaterialTheme.colorScheme.onSecondary, fontSize = 16.sp)
                }
                
                Button(
                    onClick = {
                        navController.navigate(Screen.ClientHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Client", color = MaterialTheme.colorScheme.onSecondary, fontSize = 16.sp)
                }
            }
        } else if (isOtpSent) {
            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                label = { Text(stringResource(R.string.enter_otp)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                isError = otpError,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { 
                    if (otp.isBlank()) {
                        otpError = true
                        showGenericError = true
                    } else {
                        otpError = false
                        showGenericError = false
                        isOtpVerified = true 
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Verify OTP", color = MaterialTheme.colorScheme.onSecondary, fontSize = 16.sp)
            }
        } else {
            Button(
                onClick = { 
                    if (phoneNumber.isBlank()) {
                        phoneNumberError = true
                        showGenericError = true
                    } else {
                        phoneNumberError = false
                        showGenericError = false
                        isOtpSent = true 
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Send OTP", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate(Screen.RoleSelection.route) }) {
            Text(stringResource(R.string.dont_have_account_register))
        }
    }
}
