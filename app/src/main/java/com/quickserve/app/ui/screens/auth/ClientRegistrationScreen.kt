package com.quickserve.app.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.quickserve.app.ui.navigation.Screen
import com.quickserve.app.utils.LanguageUtils
import androidx.compose.ui.res.stringResource
import com.quickserve.app.R
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientRegistrationScreen(navController: NavController) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var isPhoneOtpSent by remember { mutableStateOf(false) }
    var isPhoneOtpVerified by remember { mutableStateOf(false) }
    var phoneOtp by remember { mutableStateOf("") }

    var aadhaar by remember { mutableStateOf("") }
    var isAadhaarOtpSent by remember { mutableStateOf(false) }
    var isAadhaarOtpVerified by remember { mutableStateOf(false) }
    var aadhaarOtp by remember { mutableStateOf("") }

    var expandedLanguage by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }

    val languages = listOf("English", "Hindi", "Telugu", "Tamil", "Marathi", "Malayalam")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Client Registration",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 24.dp)
        )

        // Language Selection
        ExposedDropdownMenuBox(
            expanded = expandedLanguage,
            onExpandedChange = { expandedLanguage = !expandedLanguage }
        ) {
            OutlinedTextField(
                value = selectedLanguage,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.app_language)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLanguage) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedLanguage,
                onDismissRequest = { expandedLanguage = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = { Text(language) },
                        onClick = {
                            selectedLanguage = language
                            expandedLanguage = false
                            LanguageUtils.setLocale(context, language)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text(stringResource(R.string.full_name)) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text(stringResource(R.string.city)) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number and OTP
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newValue -> 
                if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                    phoneNumber = newValue
                }
            },
            label = { Text(stringResource(R.string.phone_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (!isPhoneOtpVerified) {
            if (isPhoneOtpSent) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phoneOtp,
                    onValueChange = { phoneOtp = it },
                    label = { Text(stringResource(R.string.enter_phone_otp)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { isPhoneOtpVerified = true }, shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().height(52.dp)) {
                    Text(stringResource(R.string.verify_phone_otp))
                }
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { isPhoneOtpSent = true }, shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().height(52.dp)) {
                    Text(stringResource(R.string.send_phone_otp))
                }
            }
        } else {
            Text("Phone Verified ✓", color = androidx.compose.ui.graphics.Color(0xFF4CAF50), modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Aadhaar / PAN and OTP
        OutlinedTextField(
            value = aadhaar,
            onValueChange = { aadhaar = it },
            label = { Text(stringResource(R.string.aadhaar_or_pan_card)) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (!isAadhaarOtpVerified) {
            if (isAadhaarOtpSent) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = aadhaarOtp,
                    onValueChange = { aadhaarOtp = it },
                    label = { Text(stringResource(R.string.enter_id_otp)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { isAadhaarOtpVerified = true }, shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().height(52.dp)) {
                    Text(stringResource(R.string.verify_id_otp))
                }
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { isAadhaarOtpSent = true }, shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().height(52.dp)) {
                    Text(stringResource(R.string.send_id_otp))
                }
            }
        } else {
            Text("ID Verified ✓", color = androidx.compose.ui.graphics.Color(0xFF4CAF50), modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Submit logic, then redirect to login
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) // Clear backstack so the user goes fresh to Login
                }
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Register", fontSize = 18.sp)
        }
    }
}
