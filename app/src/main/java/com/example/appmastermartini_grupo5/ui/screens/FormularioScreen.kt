package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmastermartini_grupo5.viewmodel.FormularioViewModel

@Composable
fun FormularioScreen(
    modifier: Modifier = Modifier,
    viewModel: FormularioViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Registro de Clientes y Emprendedores",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Recibe invitaciones a cursos técnicos y recetas de Master Martini (usar datos ficticios).",
            style = MaterialTheme.typography.bodyMedium
        )

        // Campo 1: Nombre Completo
        OutlinedTextField(
            value = state.nombre,
            onValueChange = { viewModel.onNombreChange(it) },
            label = { Text("Nombre Completo") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") },
            trailingIcon = {
                if (state.errorNombre != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                } else if (state.nombre.isNotEmpty()) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Correcto", tint = Color(0xFF4CAF50))
                }
            },
            isError = state.errorNombre != null,
            supportingText = {
                if (state.errorNombre != null) Text(state.errorNombre!!)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo 2: Correo Electrónico
        OutlinedTextField(
            value = state.correo,
            onValueChange = { viewModel.onCorreoChange(it) },
            label = { Text("Correo Electrónico") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Correo") },
            trailingIcon = {
                if (state.errorCorreo != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                } else if (state.correo.isNotEmpty()) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Correcto", tint = Color(0xFF4CAF50))
                }
            },
            isError = state.errorCorreo != null,
            supportingText = {
                if (state.errorCorreo != null) Text(state.errorCorreo!!)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo 3: Teléfono de Contacto
        OutlinedTextField(
            value = state.telefono,
            onValueChange = { viewModel.onTelefonoChange(it) },
            label = { Text("Teléfono (ej: 987654321)") },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Teléfono") },
            trailingIcon = {
                if (state.errorTelefono != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                } else if (state.telefono.isNotEmpty()) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Correcto", tint = Color(0xFF4CAF50))
                }
            },
            isError = state.errorTelefono != null,
            supportingText = {
                if (state.errorTelefono != null) Text(state.errorTelefono!!)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo 4: RUT Empresa Ficticio
        OutlinedTextField(
            value = state.rutEmpresa,
            onValueChange = { viewModel.onRutChange(it) },
            label = { Text("RUT Empresa Ficticio (ej: 76123456-7)") },
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = "RUT") },
            trailingIcon = {
                if (state.errorRut != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                } else if (state.rutEmpresa.isNotEmpty()) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Correcto", tint = Color(0xFF4CAF50))
                }
            },
            isError = state.errorRut != null,
            supportingText = {
                if (state.errorRut != null) Text(state.errorRut!!)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo 5: Rubro Gastronómico
        OutlinedTextField(
            value = state.rubroNegocio,
            onValueChange = { viewModel.onRubroChange(it) },
            label = { Text("Rubro (Pastelería, Chocolatería, Heladería...)") },
            leadingIcon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Rubro") },
            trailingIcon = {
                if (state.errorRubro != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                } else if (state.rubroNegocio.isNotEmpty()) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Correcto", tint = Color(0xFF4CAF50))
                }
            },
            isError = state.errorRubro != null,
            supportingText = {
                if (state.errorRubro != null) Text(state.errorRubro!!)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = { viewModel.enviarFormulario() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Guardar Ficha de Cliente", style = MaterialTheme.typography.titleMedium)
        }

        AnimatedVisibility(visible = state.registroExitoso) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¡Ficha validada y registrada con éxito para ${state.nombre}!",
                    color = Color(0xFF2E7D32),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}