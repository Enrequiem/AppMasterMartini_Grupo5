package com.example.appmastermartini_grupo5.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appmastermartini_grupo5.R
import com.example.appmastermartini_grupo5.model.Receta
import com.example.appmastermartini_grupo5.repository.CatalogoRepository

@Composable
fun HomeScreen(
    esModoPro: Boolean,
    nombreNegocioPro: String,
    onIrARegistro: () -> Unit,
    onSeleccionarCurso: (String) -> Unit
) {
    val repository = remember { CatalogoRepository() }
    val listaCursos = remember { repository.obtenerCursos() }

    val colorFondo by animateColorAsState(
        targetValue = if (esModoPro) Color(0xFF0E1014) else Color(0xFFFDFBFF),
        label = "ColorFondoHome"
    )
    val colorBotonPrincipal by animateColorAsState(
        targetValue = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF3B5984),
        label = "ColorBotonPrincipal"
    )
    val colorTextoTitulo = if (esModoPro) Color.White else Color(0xFF1C1B1F)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorFondo
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Master Martini Chile",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = colorTextoTitulo
                        )
                        if (esModoPro) {
                            Text(
                                text = "Portal Ejecutivo PRO • ${nombreNegocioPro.ifBlank { "Cliente VIP" }}",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    if (esModoPro) {
                        Surface(
                            color = Color(0xFFD4AF37),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "MODO PRO",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Banner Master Martini",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(240.dp)
                            .height(75.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            item {
                Button(
                    onClick = onIrARegistro,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonPrincipal,
                        contentColor = if (esModoPro) Color.Black else Color.White
                    )
                ) {
                    Text(
                        text = if (esModoPro) {
                            "Actualizar Datos de mi Negocio Pro"
                        } else {
                            "Inscribir mi Negocio Gastronómico"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(listaCursos) { curso ->
                ApartadoCursoButtonCard(
                    receta = curso,
                    esModoPro = esModoPro,
                    onClick = { onSeleccionarCurso(curso.id) }
                )
            }
        }
    }
}

@Composable
fun ApartadoCursoButtonCard(
    receta: Receta,
    esModoPro: Boolean,
    onClick: () -> Unit
) {
    val colorTarjeta = if (esModoPro) Color(0xFF1A1E26) else Color(0xFFE3E7F1)
    val colorCategoria = if (esModoPro) Color(0xFFD4AF37) else Color(0xFF3B5984)
    val colorTextoPrincipal = if (esModoPro) Color.White else Color(0xFF1C1B1F)
    val colorTextoSecundario = if (esModoPro) Color.White else Color(0xFF49454F)

    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = colorTarjeta),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = receta.categoria,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = colorCategoria
                )
                if (esModoPro) {
                    Text(
                        text = "Certificación Pro Activa",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = receta.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorTextoPrincipal
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = receta.descripcionCorta,
                style = MaterialTheme.typography.bodyMedium,
                color = colorTextoSecundario
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Duración: ${receta.duracion} • Test: 5 preguntas",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colorTextoSecundario
                )

                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorCategoria,
                        contentColor = if (esModoPro) Color.Black else Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ver Tutorial y Test",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}