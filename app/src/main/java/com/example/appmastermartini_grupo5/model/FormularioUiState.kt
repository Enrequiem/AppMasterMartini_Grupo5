package com.example.appmastermartini_grupo5.model

data class FormularioUiState(
    val nombre: String = "",
    val correo: String = "",
    val telefono: String = "",
    val rutEmpresa: String = "",
    val rubroNegocio: String = "",
    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorTelefono: String? = null,
    val errorRut: String? = null,
    val errorRubro: String? = null,
    val registroExitoso: Boolean = false
)