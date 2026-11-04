package com.example.appmastermartini_grupo5.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmastermartini_grupo5.data.EstadoDataStore
import com.example.appmastermartini_grupo5.data.UsuarioRegistrado
import com.example.appmastermartini_grupo5.model.FormularioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormularioViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = EstadoDataStore(application)

    private val _uiState = MutableStateFlow(FormularioUiState())
    val uiState: StateFlow<FormularioUiState> = _uiState.asStateFlow()

    fun onNombreNegocioChange(valor: String) {
        val error = if (valor.trim().length < 3) {
            "El nombre del negocio debe tener al menos 3 caracteres"
        } else null
        _uiState.update {
            it.copy(
                nombreNegocio = valor,
                errorNombreNegocio = error,
                registroExitoso = false
            )
        }
    }

    fun onNombreContactoChange(valor: String) {
        _uiState.update { it.copy(nombreContacto = valor, registroExitoso = false) }
    }

    fun onCorreoChange(valor: String) {
        val regexCorreo = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$")
        val error = if (!regexCorreo.matches(valor.trim())) {
            "Ingresa un correo electrónico válido (ej: chef@pasteleria.cl)"
        } else null
        _uiState.update {
            it.copy(
                correo = valor,
                errorCorreo = error,
                registroExitoso = false
            )
        }
    }

    fun onPasswordChange(valor: String) {
        val error = if (valor.length < 6) {
            "La contraseña debe tener al menos 6 caracteres"
        } else null
        _uiState.update {
            it.copy(
                password = valor,
                errorPassword = error,
                registroExitoso = false
            )
        }
    }

    fun onTelefonoChange(valor: String) {
        val soloDigitos = valor.filter { it.isDigit() || it == '+' }
        val error = if (soloDigitos.length < 8) {
            "Ingresa un teléfono válido de al menos 8 dígitos"
        } else null
        _uiState.update {
            it.copy(
                telefono = soloDigitos,
                errorTelefono = error,
                registroExitoso = false
            )
        }
    }

    fun onEspecialidadChange(valor: String) {
        _uiState.update { it.copy(especialidad = valor, registroExitoso = false) }
    }

    fun onEsCuentaProChange(esPro: Boolean) {
        _uiState.update { it.copy(esCuentaPro = esPro, registroExitoso = false) }
    }

    fun registrarCliente() {
        val estadoActual = _uiState.value
        onNombreNegocioChange(estadoActual.nombreNegocio)
        onCorreoChange(estadoActual.correo)
        onPasswordChange(estadoActual.password)
        onTelefonoChange(estadoActual.telefono)

        val validado = _uiState.value
        val formularioValido = validado.errorNombreNegocio == null &&
                validado.errorCorreo == null &&
                validado.errorPassword == null &&
                validado.errorTelefono == null &&
                validado.nombreNegocio.isNotBlank() &&
                validado.correo.isNotBlank() &&
                validado.password.isNotBlank() &&
                validado.telefono.isNotBlank()

        if (formularioValido) {
            viewModelScope.launch {
                dataStore.registrarUsuario(
                    UsuarioRegistrado(
                        nombreNegocio = validado.nombreNegocio.trim(),
                        nombreContacto = validado.nombreContacto.trim().ifBlank { "Chef Encargado" },
                        correo = validado.correo.trim().lowercase(),
                        password = validado.password,
                        telefono = validado.telefono.trim(),
                        especialidad = validado.especialidad,
                        esCuentaPro = validado.esCuentaPro
                    )
                )
                _uiState.update { it.copy(registroExitoso = true) }
            }
        }
    }
}