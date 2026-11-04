package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appmastermartini_grupo5.repository.CatalogoRepository
import com.example.appmastermartini_grupo5.viewmodel.EstadoViewModel
import com.example.appmastermartini_grupo5.viewmodel.FormularioViewModel

@Composable
fun AppNavigation(
    estadoViewModel: EstadoViewModel = viewModel(),
    formularioViewModel: FormularioViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val esModoPro by estadoViewModel.esModoPro.collectAsState()
    val usuarioRegistrado by estadoViewModel.usuarioRegistrado.collectAsState()
    val repository = remember { CatalogoRepository() }

    val colorBarraInferior = if (esModoPro) Color(0xFF1A1D24) else Color(0xFFEBF0F8)
    val colorIconoSeleccionado = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF1C1B1F)
    val colorIndicador = if (esModoPro) Color(0xFF332B14) else Color(0xFFD6E2FF)
    val colorTextoNoSeleccionado = if (esModoPro) Color(0xFF9AA0AC) else Color(0xFF49454F)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorBarraInferior
            ) {
                NavigationBarItem(
                    selected = currentRoute == "home" || currentRoute?.startsWith("detalle_curso") == true,
                    onClick = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorIconoSeleccionado,
                        selectedTextColor = colorIconoSeleccionado,
                        indicatorColor = colorIndicador,
                        unselectedIconColor = colorTextoNoSeleccionado,
                        unselectedTextColor = colorTextoNoSeleccionado
                    )
                )

                NavigationBarItem(
                    selected = currentRoute == "registro",
                    onClick = {
                        navController.navigate("registro") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Registro") },
                    label = { Text("Registro") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorIconoSeleccionado,
                        selectedTextColor = colorIconoSeleccionado,
                        indicatorColor = colorIndicador,
                        unselectedIconColor = colorTextoNoSeleccionado,
                        unselectedTextColor = colorTextoNoSeleccionado
                    )
                )

                NavigationBarItem(
                    selected = currentRoute == "modo_pro",
                    onClick = {
                        navController.navigate("modo_pro") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Modo Pro") },
                    label = { Text("Modo Pro") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorIconoSeleccionado,
                        selectedTextColor = colorIconoSeleccionado,
                        indicatorColor = colorIndicador,
                        unselectedIconColor = colorTextoNoSeleccionado,
                        unselectedTextColor = colorTextoNoSeleccionado
                    )
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    esModoPro = esModoPro,
                    nombreNegocioPro = usuarioRegistrado.nombreNegocio,
                    onIrARegistro = { navController.navigate("registro") },
                    onSeleccionarCurso = { cursoId ->
                        navController.navigate("detalle_curso/$cursoId")
                    }
                )
            }

            composable(
                route = "detalle_curso/{cursoId}",
                arguments = listOf(navArgument("cursoId") { type = NavType.StringType })
            ) { backStackEntry ->
                val cursoId = backStackEntry.arguments?.getString("cursoId") ?: "chocolateria"
                val receta = repository.obtenerCursoPorId(cursoId)
                    ?: repository.obtenerCursos().first()

                DetalleCursoScreen(
                    receta = receta,
                    esModoPro = esModoPro,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("registro") {
                FormularioScreen(
                    viewModel = formularioViewModel,
                    esModoPro = esModoPro,
                    onIrAModoPro = { navController.navigate("modo_pro") }
                )
            }

            composable("modo_pro") {
                EstadoScreen(
                    viewModel = estadoViewModel,
                    onIrARegistro = { navController.navigate("registro") }
                )
            }
        }
    }
}