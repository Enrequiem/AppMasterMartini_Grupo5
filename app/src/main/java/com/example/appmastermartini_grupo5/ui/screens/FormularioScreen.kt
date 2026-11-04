package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.appmastermartini_grupo5.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    viewModel: FormularioViewModel,
    esModoPro: Boolean,
    onIrAModoPro: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val especialidades = listOf("Chocolatería", "Pastelería de Vitrina", "Bollería y Masas")
    var menuExpandido by remember { mutableStateOf(false) }

    val colorFondo = if (esModoPro) Color(0xFF0E1014) else Color(0xFFFDFBFF)
    val colorTarjeta = if (esModoPro) Color(0xFF1A1E26) else Color.White
    val colorPrimario = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF3B5984)
    val colorTexto = if (esModoPro) Color.White else Color(0xFF1C1B1F)

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = colorTexto,
        unfocusedTextColor = colorTexto,
        focusedLabelColor = colorPrimario,
        unfocusedLabelColor = if (esModoPro) Color.White.copy(alpha = 0.85f) else Color(0xFF49454F),
        focusedBorderColor = colorPrimario,
        unfocusedBorderColor = if (esModoPro) Color.White.copy(alpha = 0.6f) else Color(0xFF79747E),
        cursorColor = colorPrimario,
        focusedLeadingIconColor = colorPrimario,
        unfocusedLeadingIconColor = if (esModoPro) Color.White else Color(0xFF49454F),
        focusedTrailingIconColor = colorPrimario,
        unfocusedTrailingIconColor = if (esModoPro) Color.White else Color(0xFF49454F),
        errorTextColor = colorTexto,
        errorSupportingTextColor = if (esModoPro) Color(0xFFFF8A80) else MaterialTheme.colorScheme.error
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Registro de Cliente Gastronómico",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = colorTexto
            )
            Text(
                text = "Registra los datos de tu negocio y elige al final si deseas habilitar tu cuenta como Usuario Pro o mantener una cuenta Básica.",
                style = MaterialTheme.typography.bodyMedium,
                color = colorTexto
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.nombreNegocio,
                        onValueChange = viewModel::onNombreNegocioChange,
                        label = { Text("Nombre del Negocio / Pastelería") },
                        isError = uiState.errorNombreNegocio != null,
                        supportingText = {
                            uiState.errorNombreNegocio?.let { Text(it) }
                        },
                        colors = textFieldColors,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = uiState.nombreContacto,
                        onValueChange = viewModel::onNombreContactoChange,
                        label = { Text("Nombre del Chef o Encargado") },
                        colors = textFieldColors,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = uiState.correo,
                        onValueChange = viewModel::onCorreoChange,
                        label = { Text("Correo Electrónico de Acceso") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isError = uiState.errorCorreo != null,
                        supportingText = {
                            uiState.errorCorreo?.let { Text(it) }
                        },
                        colors = textFieldColors,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        label = { Text("Contraseña (mín. 6 caracteres)") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isError = uiState.errorPassword != null,
                        supportingText = {
                            uiState.errorPassword?.let { Text(it) }
                        },
                        colors = textFieldColors,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = uiState.telefono,
                        onValueChange = viewModel::onTelefonoChange,
                        label = { Text("Teléfono de Contacto (+569...)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        isError = uiState.errorTelefono != null,
                        supportingText = {
                            uiState.errorTelefono?.let { Text(it) }
                        },
                        colors = textFieldColors,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    ExposedDropdownMenuBox(
                        expanded = menuExpandido,
                        onExpandedChange = { menuExpandido = !menuExpandido }
                    ) {
                        OutlinedTextField(
                            value = uiState.especialidad,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Línea Principal de Interés") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuExpandido)
                            },
                            colors = textFieldColors,
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = menuExpandido,
                            onDismissRequest = { menuExpandido = false }
                        ) {
                            especialidades.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion) },
                                    onClick = {
                                        viewModel.onEspecialidadChange(opcion)
                                        menuExpandido = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Casilla para marcar si la cuenta será Pro o Básica
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { viewModel.onEsCuentaProChange(!uiState.esCuentaPro) },
                        shape = RoundedCornerShape(12.dp),
                        color = if (esModoPro) Color(0xFF252A34) else Color(0xFFF0F4FA),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (uiState.esCuentaPro) colorPrimario else Color.Gray.copy(alpha = 0.4f)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = uiState.esCuentaPro,
                                onCheckedChange = { viewModel.onEsCuentaProChange(it) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorPrimario,
                                    uncheckedColor = colorTexto,
                                    checkmarkColor = if (esModoPro) Color.Black else Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Registrar cuenta como Usuario PRO",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = colorTexto
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = colorPrimario,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Text(
                                    text = if (uiState.esCuentaPro) {
                                        "Activado: Tendrás acceso al cambio visual Modo Pro y beneficios exclusivos."
                                    } else {
                                        "No marcado: Tu cuenta será Básica y solo tendrá acceso a las funciones estándar."
                                    },
                                    style = MaterialTheme.typography.bodySmall,
                                    color = colorTexto
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = { viewModel.registrarCliente() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorPrimario,
                            contentColor = if (esModoPro) Color.Black else Color.White
                        )
                    ) {
                        Text(
                            text = if (uiState.esCuentaPro) {
                                "Guardar Registro como Cuenta PRO"
                            } else {
                                "Guardar Registro como Cuenta Básica"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            AnimatedVisibility(visible = uiState.registroExitoso) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (esModoPro) Color(0xFF1E3324) else Color(0xFFE8F5E9)
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (esModoPro) Color(0xFF81C784) else Color(0xFF2E7D32),
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "¡Cuenta registrada correctamente!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = colorTexto
                        )
                        Text(
                            text = if (uiState.esCuentaPro) {
                                "Se registró con categoría PRO. Inicia sesión en la pestaña Modo Pro para activar la interfaz ejecutiva."
                            } else {
                                "Se registró como Cuenta Básica. Solo tendrás acceso a las funciones básicas de la aplicación."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = colorTexto
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = onIrAModoPro,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF2E7D32),
                                contentColor = if (esModoPro) Color.Black else Color.White
                            )
                        ) {
                            Text(
                                text = "Ir a la pestaña Modo Pro",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}