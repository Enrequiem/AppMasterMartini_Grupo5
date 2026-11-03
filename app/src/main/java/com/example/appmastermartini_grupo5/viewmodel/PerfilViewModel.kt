package com.example.appmastermartini_grupo1.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {

    private val _imagenUri = MutableStateFlow<Uri?>(value = null)
    val imagenUri: StateFlow<Uri?> = _imagenUri

    fun actualizarImagenGaleria(uri: Uri?) {
        if (uri != null) {
            _imagenUri.value = uri
        }
    }

    fun actualizarImagenCamara(uri: Uri?, exito: Boolean) {
        if (exito && uri != null) {
            _imagenUri.value = uri
        }
    }
}