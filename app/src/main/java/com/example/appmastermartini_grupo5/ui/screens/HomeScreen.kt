package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmastermartini_grupo5.R
import com.example.appmastermartini_grupo5.model.Receta
import com.example.appmastermartini_grupo5.repository.CatalogoRepository
import com.example.appmastermartini_grupo5.ui.utils.obtenerWindowSizeClass

@Composable
fun HomeScreen2(onIrARegistro: () -> Unit = {}) {
    val windowSizeClass = obtenerWindowSizeClass()
    val recetas = remember { CatalogoRepository().obtenerRecetas() }

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompacta(recetas, onIrARegistro)
        WindowWidthSizeClass.Medium -> HomeScreenMediana(recetas, onIrARegistro)
        WindowWidthSizeClass.Expanded -> HomeScreenExpandida(recetas, onIrARegistro)
        else -> HomeScreenCompacta(recetas, onIrARegistro)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompacta(recetas: List<Receta>, onIrARegistro: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Master Martini Chile") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            item {
                Text(
                    text = "¡Bienvenido al Portal Técnico!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Master Martini",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 140.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onIrARegistro,
                    modifier = Modifier.fillMaxWidth().height(52.dp)
                ) {
                    Text(text = "Inscribir mi Negocio Gastronómico")
                }
            }
            items(recetas) { receta ->
                TarjetaReceta(receta)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMediana(recetas: List<Receta>, onIrARegistro: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Master Martini - Vista Tablet/Mediana") })
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .padding(all = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Portal Técnico Gastronómico",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Master Martini",
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    contentScale = ContentScale.Fit
                )
                Button(onClick = onIrARegistro, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Inscribir Negocio")
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1.5f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recetas) { receta ->
                    TarjetaReceta(receta)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpandida(recetas: List<Receta>, onIrARegistro: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Master Martini - Panel Expandido") })
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .padding(all = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Master Martini Chile SPA",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Capacitación continua en chocolatería, pastelería, heladería y panadería.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Master Martini",
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentScale = ContentScale.Fit
                )
                Button(onClick = onIrARegistro, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    Text(text = "Registrar Cliente / Prospecto")
                }
            }
            LazyColumn(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recetas) { receta ->
                    TarjetaReceta(receta)
                }
            }
        }
    }
}

@Composable
fun TarjetaReceta(receta: Receta) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = receta.categoria, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Text(text = receta.titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = receta.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Duración: ${receta.duracion}", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview(name = "Compact", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
fun PreviewCompact() {
    HomeScreenCompacta(recetas = CatalogoRepository().obtenerRecetas())
}

@Preview(name = "Medium", widthDp = 650, heightDp = 800, showBackground = true)
@Composable
fun PreviewMedium() {
    HomeScreenMediana(recetas = CatalogoRepository().obtenerRecetas())
}

@Preview(name = "Expanded", widthDp = 900, heightDp = 800, showBackground = true)
@Composable
fun PreviewExpanded() {
    HomeScreenExpandida(recetas = CatalogoRepository().obtenerRecetas())
}