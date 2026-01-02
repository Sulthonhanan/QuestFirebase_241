package com.example.questfirebase.view.controlNavigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questfirebase.view.route.DestinasiHome
import com.example.questfirebase.view.route.DestinasiEntry
import com.example.questfirebase.view.route.DestinasiDetail
import com.example.questfirebase.view.HalamanHome
import com.example.questfirebase.view.HalamanEntry

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HalamanHome(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDetail.route}/$nidn")
                }
            )
        }
        composable(DestinasiEntry.route) {
            HalamanEntry(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}