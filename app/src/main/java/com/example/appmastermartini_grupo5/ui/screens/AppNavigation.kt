package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Imports locales forzados al grupo 5 para evitar errores de referencia
import com.example.appmastermartini_grupo5.ui.screens.HomeScreen2
import com.example.appmastermartini_grupo5.ui.screens.FormularioScreen
import com.example.appmastermartini_grupo5.ui.screens.PantallaPrincipal

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "inicio"

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "inicio",
                    onClick = { navController.navigate("inicio") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = currentRoute == "formulario",
                    onClick = { navController.navigate("formulario") },
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Registro") },
                    label = { Text("Registro") }
                )
                NavigationBarItem(
                    selected = currentRoute == "estado",
                    onClick = { navController.navigate("estado") },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Modo Pro") },
                    label = { Text("Modo Pro") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") {
                HomeScreen2(onIrARegistro = { navController.navigate("formulario") })
            }
            composable("formulario") {
                FormularioScreen()
            }
            composable("estado") {
                PantallaPrincipal()
            }
        }
    }
}