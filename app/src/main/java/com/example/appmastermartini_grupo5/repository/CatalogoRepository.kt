package com.example.appmastermartini_grupo5.repository

import com.example.appmastermartini_grupo5.model.Receta

class CatalogoRepository {
    fun obtenerRecetas(): List<Receta> {
        return listOf(
            Receta(
                id = 1,
                titulo = "Templado de Chocolate Ariba",
                categoria = "Chocolatería",
                duracion = "25 min",
                descripcion = "Curso técnico paso a paso para lograr brillo y quiebre perfecto en bombonería."
            ),
            Receta(
                id = 2,
                titulo = "Montado de Crema Vegetal Decor Up",
                categoria = "Pastelería de Vitrina",
                duracion = "15 min",
                descripcion = "Rendimiento óptimo y estabilidad para tortas y postres de vitrina."
            ),
            Receta(
                id = 3,
                titulo = "Laminado con Margarina de Hoja",
                categoria = "Bollería y Masas",
                duracion = "40 min",
                descripcion = "Técnica de pliegues simples y dobles para croissants y milhojas crujientes."
            )
        )
    }
}