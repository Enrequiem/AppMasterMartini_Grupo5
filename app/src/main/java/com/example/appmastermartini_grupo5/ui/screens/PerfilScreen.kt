package com.example.appmastermartini_grupo1.ui.screens

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmastermartini_grupo1.ui.components.ImagenInteligente
import com.example.appmastermartini_grupo1.viewmodel.PerfilViewModel
import java.io.File

@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
    viewModel: PerfilViewModel = viewModel()
) {
    val context = LocalContext.current
    val imagenUri by viewModel.imagenUri.collectAsState()
    var uriTemporalCamara by remember { mutableStateOf<Uri?>(null) }

    val launcherGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.actualizarImagenGaleria(uri)
    }

    val launcherCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito: Boolean ->
        viewModel.actualizarImagenCamara(uriTemporalCamara, exito)
    }

    val launcherPermisoCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido: Boolean ->
        if (concedido) {
            val nuevoUri = crearUriTemporal(context)
            uriTemporalCamara = nuevoUri
            launcherCamara.launch(nuevoUri)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Perfil del Maestro Pastelero / Local",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sube el logo de tu negocio o captura una foto de tus creaciones con insumos Master Martini.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(28.dp))

        ImagenInteligente(imagenUri = imagenUri)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { launcherGaleria.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(text = "Seleccionar desde Galería")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { launcherPermisoCamara.launch(Manifest.permission.CAMERA) },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(text = "Capturar con Cámara")
        }
    }
}

private fun crearUriTemporal(context: Context): Uri {
    val archivo = File.createTempFile(
        "foto_perfil_${System.currentTimeMillis()}",
        ".jpg",
        context.cacheDir
    )
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        archivo
    )
}