package com.example.appmastermartini_grupo5.model

data class FormularioUiState(
    val nombreNegocio: String = "",
    val nombreContacto: String = "",
    val correo: String = "",
    val password: String = "",
    val telefono: String = "",
    val especialidad: String = "Chocolatería",
    val esCuentaPro: Boolean = false,
    val errorNombreNegocio: String? = null,
    val errorCorreo: String? = null,
    val errorPassword: String? = null,
    val errorTelefono: String? = null,
    val registroExitoso: Boolean = false
)