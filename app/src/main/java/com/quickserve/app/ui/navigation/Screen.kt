package com.quickserve.app.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object RoleSelection : Screen("role_selection")
    object WorkerRegistration : Screen("worker_registration")
    object WorkerHome : Screen("worker_home")
    object ClientHome : Screen("client_home")
    object WorkerDashboard : Screen("worker_dashboard")
    object WorkerProfile : Screen("worker_profile")
    object ClientBookings : Screen("client_bookings")
    object ClientProfile : Screen("client_profile")
    object ClientRegistration : Screen("client_registration")
}
