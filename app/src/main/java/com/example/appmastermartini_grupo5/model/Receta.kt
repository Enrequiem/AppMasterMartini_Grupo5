package com.example.appmastermartini_grupo5.model

data class PreguntaTest(
    val enunciado: String,
    val opciones: List<String>,
    val indiceRespuestaCorrecta: Int,
    val explicacion: String
)

data class Receta(
    val id: String,
    val categoria: String,
    val titulo: String,
    val descripcionCorta: String,
    val duracion: String,
    val introduccionDetallada: String,
    val youtubeVideoId: String,
    val preguntas: List<PreguntaTest>
)