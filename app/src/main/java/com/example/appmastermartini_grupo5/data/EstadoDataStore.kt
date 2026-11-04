package com.example.appmastermartini_grupo5.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "master_martini_prefs")

data class UsuarioRegistrado(
    val nombreNegocio: String = "",
    val nombreContacto: String = "",
    val correo: String = "",
    val password: String = "",
    val telefono: String = "",
    val especialidad: String = "",
    val esCuentaPro: Boolean = false
) {
    val estaRegistrado: Boolean
        get() = correo.isNotBlank() && password.isNotBlank()
}

class EstadoDataStore(private val context: Context) {

    companion object {
        val MODO_PRO_KEY = booleanPreferencesKey("modo_pro_activo")
        val SESION_PRO_INICIADA_KEY = booleanPreferencesKey("sesion_pro_iniciada")
        val NEGOCIO_KEY = stringPreferencesKey("usuario_negocio")
        val CONTACTO_KEY = stringPreferencesKey("usuario_contacto")
        val CORREO_KEY = stringPreferencesKey("usuario_correo")
        val PASSWORD_KEY = stringPreferencesKey("usuario_password")
        val TELEFONO_KEY = stringPreferencesKey("usuario_telefono")
        val ESPECIALIDAD_KEY = stringPreferencesKey("usuario_especialidad")
        val ES_CUENTA_PRO_KEY = booleanPreferencesKey("usuario_es_cuenta_pro")
    }

    val modoProFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[MODO_PRO_KEY] ?: false
    }

    val sesionIniciadaFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[SESION_PRO_INICIADA_KEY] ?: false
    }

    val usuarioRegistradoFlow: Flow<UsuarioRegistrado> = context.dataStore.data.map { preferences ->
        UsuarioRegistrado(
            nombreNegocio = preferences[NEGOCIO_KEY] ?: "",
            nombreContacto = preferences[CONTACTO_KEY] ?: "",
            correo = preferences[CORREO_KEY] ?: "",
            password = preferences[PASSWORD_KEY] ?: "",
            telefono = preferences[TELEFONO_KEY] ?: "",
            especialidad = preferences[ESPECIALIDAD_KEY] ?: "Chocolatería",
            esCuentaPro = preferences[ES_CUENTA_PRO_KEY] ?: false
        )
    }

    suspend fun registrarUsuario(usuario: UsuarioRegistrado) {
        context.dataStore.edit { preferences ->
            preferences[NEGOCIO_KEY] = usuario.nombreNegocio
            preferences[CONTACTO_KEY] = usuario.nombreContacto
            preferences[CORREO_KEY] = usuario.correo.trim().lowercase()
            preferences[PASSWORD_KEY] = usuario.password
            preferences[TELEFONO_KEY] = usuario.telefono
            preferences[ESPECIALIDAD_KEY] = usuario.especialidad
            preferences[ES_CUENTA_PRO_KEY] = usuario.esCuentaPro
            if (!usuario.esCuentaPro) {
                preferences[MODO_PRO_KEY] = false
            }
        }
    }

    suspend fun cambiarEstadoSesion(iniciada: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SESION_PRO_INICIADA_KEY] = iniciada
            if (!iniciada) {
                preferences[MODO_PRO_KEY] = false
            }
        }
    }

    suspend fun cambiarModoPro(activo: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MODO_PRO_KEY] = activo
        }
    }
}