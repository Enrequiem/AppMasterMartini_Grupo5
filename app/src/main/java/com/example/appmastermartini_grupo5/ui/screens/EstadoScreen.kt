package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmastermartini_grupo1.viewmodel.EstadoViewModel

@Composable
fun PantallaPrincipal(
    modifier: Modifier = Modifier,
    viewModel: EstadoViewModel = viewModel()
) {
    val estado = viewModel.activo.collectAsState()
    val mostrarMensaje = viewModel.mostrarMensaje.collectAsState()

    if (estado.value == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val estaActivo = estado.value!!
        val colorAnimado by animateColorAsState(
            targetValue = if (estaActivo) Color(color = 0xFF4CAF50) else Color(color = 0xFFB0BEC5),
            animationSpec = tween(durationMillis = 500),
            label = "colorModoEspecial"
        )
        val textoBoton by remember(key1 = estaActivo) {
            derivedStateOf { if (estaActivo) "Desactivar Modo Especial" else "Activar Modo Especial" }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Suscripción a Recetas y Alertas Técnicas",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (estaActivo) "Estado actual: SUSCRIPCIÓN ACTIVA (Guardado en DataStore)"
                else "Estado actual: INACTIVO",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.alternarEstado() },
                colors = ButtonDefaults.buttonColors(containerColor = colorAnimado),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 60.dp)
            ) {
                Text(text = textoBoton, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(height = 24.dp))

            AnimatedVisibility(visible = mostrarMensaje.value) {
                Text(
                    text = "¡Estado guardado exitosamente!",
                    color = Color(color = 0xFF4CAF50),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}