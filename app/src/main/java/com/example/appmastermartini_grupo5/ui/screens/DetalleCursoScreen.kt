package com.example.appmastermartini_grupo5.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.appmastermartini_grupo5.model.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleCursoScreen(
    receta: Receta,
    esModoPro: Boolean,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val respuestasSeleccionadas = remember { mutableStateMapOf<Int, Int>() }
    var testEnviado by remember { mutableStateOf(false) }

    val colorPrimario = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF3B5984)
    val colorFondo = if (esModoPro) Color(0xFF0E1014) else Color(0xFFF7F8FC)
    val colorTarjeta = if (esModoPro) Color(0xFF1A1E26) else Color.White
    val colorTexto = if (esModoPro) Color.White else Color(0xFF1C1B1F)

    val puntajeObtenido = remember(testEnviado, respuestasSeleccionadas.toMap()) {
        receta.preguntas.indices.count { index ->
            respuestasSeleccionadas[index] == receta.preguntas[index].indiceRespuestaCorrecta
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = receta.categoria,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        if (esModoPro) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = Color(0xFFD4AF37),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "PRO",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al inicio",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (esModoPro) Color(0xFF1A1D24) else Color(0xFF3B5984)
                )
            )
        },
        containerColor = colorFondo
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = receta.titulo,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorTexto
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Duración estimada: ${receta.duracion}",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorPrimario
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Introducción al Módulo",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = colorPrimario
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = receta.introduccionDetallada,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorTexto
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Video Tutorial: ${receta.categoria}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorTexto
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    YouTubeVideoPlayer(
                        videoId = receta.youtubeVideoId,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.youtube.com/watch?v=${receta.youtubeVideoId}")
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, if (esModoPro) Color.White else colorPrimario)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = if (esModoPro) Color.White else colorPrimario
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Abrir tutorial directamente en YouTube",
                            color = if (esModoPro) Color.White else colorPrimario,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Test de Conocimientos (5 Preguntas)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = colorTexto
                            )
                            Text(
                                text = "Selecciona la alternativa correcta en cada pregunta para evaluar tu dominio en ${receta.categoria}.",
                                style = MaterialTheme.typography.bodySmall,
                                color = colorTexto
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = colorPrimario,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    receta.preguntas.forEachIndexed { indexPregunta, pregunta ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .background(
                                    color = if (esModoPro) Color(0xFF252A34) else Color(0xFFF1F3F9),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Text(
                                text = pregunta.enunciado,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = colorTexto
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            pregunta.opciones.forEachIndexed { indexOpcion, textoOpcion ->
                                val seleccionada = respuestasSeleccionadas[indexPregunta] == indexOpcion
                                val esCorrecta = indexOpcion == pregunta.indiceRespuestaCorrecta

                                val colorBordeOpcion = when {
                                    !testEnviado && seleccionada -> colorPrimario
                                    testEnviado && esCorrecta -> Color(0xFF4CAF50)
                                    testEnviado && seleccionada && !esCorrecta -> Color(0xFFEF5350)
                                    else -> Color.Transparent
                                }

                                val colorFondoOpcion = when {
                                    !testEnviado && seleccionada -> colorPrimario.copy(alpha = 0.2f)
                                    testEnviado && esCorrecta -> Color(0xFF2E7D32).copy(alpha = 0.25f)
                                    testEnviado && seleccionada && !esCorrecta -> Color(0xFFC62828).copy(alpha = 0.25f)
                                    else -> Color.Transparent
                                }

                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable(enabled = !testEnviado) {
                                            respuestasSeleccionadas[indexPregunta] = indexOpcion
                                        },
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, colorBordeOpcion),
                                    color = colorFondoOpcion
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                    ) {
                                        RadioButton(
                                            selected = seleccionada,
                                            onClick = if (!testEnviado) {
                                                { respuestasSeleccionadas[indexPregunta] = indexOpcion }
                                            } else null,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = colorPrimario,
                                                unselectedColor = colorTexto
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = textoOpcion,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = colorTexto
                                        )
                                    }
                                }
                            }

                            AnimatedVisibility(visible = testEnviado) {
                                val acerto = respuestasSeleccionadas[indexPregunta] == pregunta.indiceRespuestaCorrecta
                                Column(modifier = Modifier.padding(top = 8.dp)) {
                                    Text(
                                        text = if (acerto) "Correcto" else "Incorrecto",
                                        fontWeight = FontWeight.Bold,
                                        color = if (acerto) Color(0xFF81C784) else Color(0xFFFF8A80),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                    Text(
                                        text = pregunta.explicacion,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colorTexto
                                    )
                                }
                            }
                        }
                    }

                    if (!testEnviado) {
                        Button(
                            onClick = { testEnviado = true },
                            enabled = respuestasSeleccionadas.size == receta.preguntas.size,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorPrimario,
                                contentColor = if (esModoPro) Color.Black else Color.White
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (respuestasSeleccionadas.size != receta.preguntas.size) {
                                    "Responde las 5 preguntas (${respuestasSeleccionadas.size}/5)"
                                } else {
                                    "Evaluar Conocimientos"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (puntajeObtenido >= 4) {
                                    Color(0xFF2E7D32).copy(alpha = 0.25f)
                                } else {
                                    Color(0xFFF57C00).copy(alpha = 0.25f)
                                }
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Resultado Final: $puntajeObtenido de 5 respuestas correctas",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = colorTexto
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (puntajeObtenido >= 4) {
                                        "Excelente dominio técnico del módulo ${receta.categoria}."
                                    } else {
                                        "Te recomendamos repasar el video tutorial e intentarlo nuevamente."
                                    },
                                    style = MaterialTheme.typography.bodySmall,
                                    color = colorTexto
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedButton(
                                    onClick = {
                                        respuestasSeleccionadas.clear()
                                        testEnviado = false
                                    },
                                    border = BorderStroke(1.dp, colorTexto)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = null,
                                        tint = colorTexto
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Reintentar Test", color = colorTexto)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubeVideoPlayer(
    videoId: String,
    modifier: Modifier = Modifier
) {
    val htmlEmbed = remember(videoId) {
        """
        <!DOCTYPE html>
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body, html { margin: 0; padding: 0; width: 100%; height: 100%; background-color: #000; overflow: hidden; }
                iframe { width: 100%; height: 100%; border: 0; }
            </style>
        </head>
        <body>
            <iframe 
                src="https://www.youtube.com/embed/$videoId?rel=0&playsinline=1" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen>
            </iframe>
        </body>
        </html>
        """.trimIndent()
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.mediaPlaybackRequiresUserGesture = false
                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean = false
                }
                loadDataWithBaseURL(
                    "https://www.youtube.com",
                    htmlEmbed,
                    "text/html",
                    "utf-8",
                    null
                )
            }
        }
    )
}