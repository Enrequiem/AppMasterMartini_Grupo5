package com.example.appmastermartini_grupo5.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmastermartini_grupo5.data.EstadoDataStore
import com.example.appmastermartini_grupo5.data.UsuarioRegistrado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = EstadoDataStore(application)

    val esModoPro: StateFlow<Boolean> = dataStore.modoProFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val sesionIniciada: StateFlow<Boolean> = dataStore.sesionIniciadaFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val usuarioRegistrado: StateFlow<UsuarioRegistrado> = dataStore.usuarioRegistradoFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UsuarioRegistrado()
    )

    private val _mensajeErrorLogin = MutableStateFlow<String?>(null)
    val mensajeErrorLogin: StateFlow<String?> = _mensajeErrorLogin.asStateFlow()

    fun iniciarSesion(correoIngresado: String, passwordIngresada: String) {
        viewModelScope.launch {
            val usuario = dataStore.usuarioRegistradoFlow.first()
            if (!usuario.estaRegistrado) {
                _mensajeErrorLogin.value = "Aún no existe una cuenta registrada. Regístrate primero en la pestaña Registro."
                return@launch
            }

            val correoLimpio = correoIngresado.trim().lowercase()
            if (correoLimpio == usuario.correo.lowercase() && passwordIngresada == usuario.password) {
                _mensajeErrorLogin.value = null
                dataStore.cambiarEstadoSesion(true)
                if (!usuario.esCuentaPro) {
                    dataStore.cambiarModoPro(false)
                }
            } else {
                _mensajeErrorLogin.value = "Correo o contraseña incorrectos. Verifica las credenciales registradas."
            }
        }
    }

    fun alternarModoPro(nuevoEstado: Boolean) {
        viewModelScope.launch {
            val usuario = usuarioRegistrado.value
            if (!sesionIniciada.value) {
                _mensajeErrorLogin.value = "Debes iniciar sesión para cambiar de modo."
                return@launch
            }
            if (!usuario.esCuentaPro) {
                _mensajeErrorLogin.value = "Esta cuenta fue registrada como Básica. Solo tiene acceso a las funciones estándar."
                dataStore.cambiarModoPro(false)
                return@launch
            }
            dataStore.cambiarModoPro(nuevoEstado)
        }
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            dataStore.cambiarEstadoSesion(false)
            _mensajeErrorLogin.value = null
        }
    }

    fun limpiarError() {
        _mensajeErrorLogin.value = null
    }
}