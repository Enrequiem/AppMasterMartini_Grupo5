package com.example.appmastermartini_grupo5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appmastermartini_grupo5.model.FormularioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormularioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FormularioUiState())
    val uiState: StateFlow<FormularioUiState> = _uiState

    fun onNombreChange(valor: String) {
        val error = if (valor.trim().length < 3) "El nombre debe tener al menos 3 letras" else null
        _uiState.value = _uiState.value.copy(
            nombre = valor,
            errorNombre = error,
            registroExitoso = false
        )
    }

    fun onCorreoChange(valor: String) {
        val esValido = valor.contains("@") && valor.contains(".")
        val error = if (!esValido) "Ingrese un correo válido (ej: contacto@pasteleria.cl)" else null
        _uiState.value = _uiState.value.copy(
            correo = valor,
            errorCorreo = error,
            registroExitoso = false
        )
    }

    fun onTelefonoChange(valor: String) {
        val soloNumeros = valor.all { it.isDigit() || it == '+' }
        val error = if (!soloNumeros || valor.length < 8) "Teléfono inválido (mínimo 8 dígitos)" else null
        _uiState.value = _uiState.value.copy(
            telefono = valor,
            errorTelefono = error,
            registroExitoso = false
        )
    }

    fun onRutChange(valor: String) {
        val esValido = valor.contains("-") && valor.length >= 9
        val error = if (!esValido) "Formato RUT ficticio inválido (ej: 76123456-7)" else null
        _uiState.value = _uiState.value.copy(
            rutEmpresa = valor,
            errorRut = error,
            registroExitoso = false
        )
    }

    fun onRubroChange(valor: String) {
        val error = if (valor.trim().isEmpty()) "Indique su rubro (Panadería, Pastelería, Chocolatería, etc.)" else null
        _uiState.value = _uiState.value.copy(
            rubroNegocio = valor,
            errorRubro = error,
            registroExitoso = false
        )
    }

    fun enviarFormulario() {
        onNombreChange(_uiState.value.nombre)
        onCorreoChange(_uiState.value.correo)
        onTelefonoChange(_uiState.value.telefono)
        onRutChange(_uiState.value.rutEmpresa)
        onRubroChange(_uiState.value.rubroNegocio)

        val actual = _uiState.value
        val sinErrores = actual.errorNombre == null &&
                actual.errorCorreo == null &&
                actual.errorTelefono == null &&
                actual.errorRut == null &&
                actual.errorRubro == null

        if (sinErrores) {
            _uiState.value = actual.copy(registroExitoso = true)
        }
    }
}