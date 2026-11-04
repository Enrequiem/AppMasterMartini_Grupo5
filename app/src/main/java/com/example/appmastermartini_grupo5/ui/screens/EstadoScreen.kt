package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.appmastermartini_grupo5.viewmodel.EstadoViewModel

@Composable
fun EstadoScreen(
    viewModel: EstadoViewModel,
    onIrARegistro: () -> Unit
) {
    val esModoPro by viewModel.esModoPro.collectAsState()
    val sesionIniciada by viewModel.sesionIniciada.collectAsState()
    val usuarioRegistrado by viewModel.usuarioRegistrado.collectAsState()
    val errorLogin by viewModel.mensajeErrorLogin.collectAsState()

    var correoInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    val colorFondo by animateColorAsState(
        targetValue = if (esModoPro) Color(0xFF0E1014) else Color(0xFFF4F6FA),
        animationSpec = tween(500),
        label = "FondoModoPro"
    )
    val colorTarjeta by animateColorAsState(
        targetValue = if (esModoPro) Color(0xFF1A1E26) else Color.White,
        animationSpec = tween(500),
        label = "TarjetaModoPro"
    )
    val colorAcento by animateColorAsState(
        targetValue = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF3B5984),
        animationSpec = tween(500),
        label = "AcentoModoPro"
    )
    val colorTexto = if (esModoPro) Color.White else Color(0xFF1C1B1F)

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = colorTexto,
        unfocusedTextColor = colorTexto,
        focusedLabelColor = colorAcento,
        unfocusedLabelColor = if (esModoPro) Color.White.copy(alpha = 0.85f) else Color(0xFF49454F),
        focusedBorderColor = colorAcento,
        unfocusedBorderColor = if (esModoPro) Color.White.copy(alpha = 0.6f) else Color(0xFF79747E),
        cursorColor = colorAcento
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorFondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Encabezado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (esModoPro) "Panel Ejecutivo Master Martini PRO" else "Portal de Cuenta y Modo Pro",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorTexto
                    )
                    Text(
                        text = if (sesionIniciada) {
                            "Sesión activa: ${usuarioRegistrado.nombreNegocio}"
                        } else {
                            "Inicia sesión con tu cuenta registrada"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (esModoPro) Color.White else colorAcento
                    )
                }
                Icon(
                    imageVector = if (sesionIniciada && usuarioRegistrado.esCuentaPro) Icons.Default.Star else Icons.Default.Lock,
                    contentDescription = null,
                    tint = colorAcento,
                    modifier = Modifier.size(34.dp)
                )
            }

            // VISTA 1: Sin sesión iniciada -> Formulario de Login
            if (!sesionIniciada) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesión",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = colorTexto
                        )
                        Text(
                            text = "Ingresa el correo y contraseña creados en la pestaña Registro. Si marcaste la casilla Pro al registrarte, podrás activar la interfaz Modo Pro.",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorTexto
                        )

                        if (!usuarioRegistrado.estaRegistrado) {
                            Surface(
                                color = Color(0xFFFFF3E0),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color(0xFFFFB74D)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "Aún no hay ninguna cuenta registrada en el dispositivo.",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE65100)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    OutlinedButton(onClick = onIrARegistro) {
                                        Text("Ir a la pestaña Registro")
                                    }
                                }
                            }
                        }

                        OutlinedTextField(
                            value = correoInput,
                            onValueChange = {
                                correoInput = it
                                viewModel.limpiarError()
                            },
                            label = { Text("Correo Electrónico Registrado") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = textFieldColors,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = passwordInput,
                            onValueChange = {
                                passwordInput = it
                                viewModel.limpiarError()
                            },
                            label = { Text("Contraseña") },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            colors = textFieldColors,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        errorLogin?.let { mensaje ->
                            Text(
                                text = mensaje,
                                color = if (esModoPro) Color(0xFFFF8A80) else MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = { viewModel.iniciarSesion(correoInput, passwordInput) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorAcento,
                                contentColor = if (esModoPro) Color.Black else Color.White
                            )
                        ) {
                            Text(
                                text = "Ingresar a mi Cuenta",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } else {
                // VISTA 2: Sesión iniciada -> Verifica si la cuenta es PRO o BÁSICA
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                    shape = RoundedCornerShape(16.dp),
                    border = if (esModoPro) BorderStroke(1.5.dp, Color(0xFFD4AF37)) else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = colorAcento,
                                modifier = Modifier.size(44.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = usuarioRegistrado.nombreNegocio,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = colorTexto
                                )
                                Text(
                                    text = "${usuarioRegistrado.nombreContacto} • ${usuarioRegistrado.correo}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = colorTexto
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Surface(
                                    color = if (usuarioRegistrado.esCuentaPro) Color(0xFFD4AF37) else Color(0xFFD6E2FF),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = if (usuarioRegistrado.esCuentaPro) "CUENTA PRO AUTORIZADA" else "CUENTA BÁSICA ESTÁNDAR",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.Black,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }

                        HorizontalDivider(color = colorTexto.copy(alpha = 0.2f))

                        if (usuarioRegistrado.esCuentaPro) {
                            // Si la cuenta FUE registrada como PRO, puede activar el Switch visual
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Interfaz Visual Modo Pro",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = colorTexto
                                    )
                                    Text(
                                        text = if (esModoPro) {
                                            "Modo Pro activo: Contraste en negro y blanco con detalles dorados."
                                        } else {
                                            "Activa el interruptor para transformar visualmente toda la aplicación."
                                        },
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colorTexto
                                    )
                                }
                                Switch(
                                    checked = esModoPro,
                                    onCheckedChange = { viewModel.alternarModoPro(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.Black,
                                        checkedTrackColor = Color(0xFFD4AF37)
                                    )
                                )
                            }
                        } else {
                            // Si la cuenta NO fue registrada como PRO, solo tiene derecho a cosas básicas
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = colorAcento,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Acceso Limitado a Funciones Básicas",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = colorTexto
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Esta cuenta fue registrada sin marcar la casilla Pro. Tienes acceso a los tutoriales y cuestionarios básicos, pero el Modo Pro está deshabilitado.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colorTexto
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedButton(onClick = onIrARegistro) {
                                        Text(
                                            text = "Actualizar mi cuenta a Pro en Registro",
                                            color = colorAcento,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Tarjeta visual de Beneficios Exclusivos cuando el Modo Pro está encendido
                AnimatedVisibility(visible = esModoPro && usuarioRegistrado.esCuentaPro) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1E26)),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFD4AF37)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Beneficios Pro Activos • ${usuarioRegistrado.nombreNegocio}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Especialidad registrada: ${usuarioRegistrado.especialidad} | Contacto: ${usuarioRegistrado.telefono}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                            HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
                            BeneficioProItem("Interfaz ejecutiva de alto contraste habilitada en todas las pantallas")
                            BeneficioProItem("Certificación técnica Pro habilitada en los 3 módulos de evaluación")
                            BeneficioProItem("Soporte técnico directo con asesores gastronómicos Master Martini Chile")
                        }
                    }
                }

                OutlinedButton(
                    onClick = { viewModel.cerrarSesion() },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, if (esModoPro) Color.White else colorAcento)
                ) {
                    Text(
                        text = "Cerrar Sesión",
                        color = if (esModoPro) Color.White else colorAcento,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun BeneficioProItem(texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFFD4AF37),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}