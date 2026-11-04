package com.example.appmastermartini_grupo5.repository

import com.example.appmastermartini_grupo5.model.PreguntaTest
import com.example.appmastermartini_grupo5.model.Receta

class CatalogoRepository {

    fun obtenerCursos(): List<Receta> {
        return listOf(
            Receta(
                id = "chocolateria",
                categoria = "Chocolatería",
                titulo = "Templado de Chocolate Ariba",
                descripcionCorta = "Curso técnico paso a paso para lograr brillo y quiebre perfecto en bombonería.",
                duracion = "25 min",
                introduccionDetallada = "Bienvenido al módulo profesional de Chocolatería Master Martini. " +
                        "El templado o precristalización de la línea Ariba tiene como propósito alinear los cristales " +
                        "de manteca de cacao en su forma estable Beta (V). Este proceso garantiza un desmolde limpio, " +
                        "un brillo espejo duradero, ausencia de vetas grises (fat bloom) y un quiebre sonoro (snap) " +
                        "indispensable en bombonería fina y chocolatería de autor.",
                youtubeVideoId = "UL4du43U0Hc",
                preguntas = listOf(
                    PreguntaTest(
                        enunciado = "1. ¿Cuál es el objetivo principal del templado en el chocolate Ariba?",
                        opciones = listOf(
                            "Aumentar el dulzor del cacao",
                            "Estabilizar los cristales Beta de la manteca de cacao",
                            "Evaporar el agua contenida en la cobertura",
                            "Ablandar el chocolate para rellenos líquidos"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Los cristales Beta otorgan brillo espejo, contracción para el desmolde y quiebre firme."
                    ),
                    PreguntaTest(
                        enunciado = "2. ¿Cuál es el rango de temperatura de fusión inicial para el chocolate negro Ariba?",
                        opciones = listOf(
                            "30 °C a 32 °C",
                            "45 °C a 50 °C",
                            "65 °C a 70 °C",
                            "20 °C a 22 °C"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Fundir entre 45 °C y 50 °C disuelve por completo todos los cristales previos sin quemar los sólidos."
                    ),
                    PreguntaTest(
                        enunciado = "3. ¿A qué temperatura de trabajo final se utiliza el chocolate negro tras enfriarlo?",
                        opciones = listOf(
                            "31 °C a 32 °C",
                            "24 °C a 25 °C",
                            "38 °C a 40 °C",
                            "18 °C a 20 °C"
                        ),
                        indiceRespuestaCorrecta = 0,
                        explicacion = "Entre 31 °C y 32 °C se mantiene la fluidez óptima conservando los cristales Beta estables."
                    ),
                    PreguntaTest(
                        enunciado = "4. ¿Qué fenómeno ocurre si el chocolate se moldea sin realizar una curva de templado?",
                        opciones = listOf(
                            "Se carameliza instantáneamente",
                            "Aparece Fat Bloom (manchas blancas/grises) y no contrae",
                            "Aumenta su porcentaje de cacao amargo",
                            "Se vuelve transparente"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Sin precristalización, las grasas migran a la superficie generando manchas opacas y textura arenosa."
                    ),
                    PreguntaTest(
                        enunciado = "5. ¿Qué elemento debe evitarse estrictamente durante el fundido del chocolate?",
                        opciones = listOf(
                            "Espátulas de silicona",
                            "Bowls de acero inoxidable",
                            "Contacto con agua o vapor directo",
                            "Termómetro infrarrojo"
                        ),
                        indiceRespuestaCorrecta = 2,
                        explicacion = "Incluso unas gotas de agua provocan que el azúcar forme jarabe y la cobertura se empaste (seize)."
                    )
                )
            ),
            Receta(
                id = "pasteleria_vitrina",
                categoria = "Pastelería de Vitrina",
                titulo = "Montado de Crema Vegetal Decor Up",
                descripcionCorta = "Rendimiento óptimo y estabilidad para tortas y postres de vitrina.",
                duracion = "15 min",
                introduccionDetallada = "Bienvenido al módulo de Pastelería de Vitrina con Crema Vegetal Decor Up. " +
                        "Las cremas vegetales UHT de Master Martini están diseñadas para cuadruplicar su volumen original, " +
                        "ofreciendo una blancura impecable, textura sedosa y máxima resistencia a la sinéresis (pérdida de líquido) " +
                        "en vitrinas refrigeradas durante varios días.",
                youtubeVideoId = "d6Ih7ckl_gY",
                preguntas = listOf(
                    PreguntaTest(
                        enunciado = "1. ¿Cuál es la temperatura ideal de la crema Decor Up antes de ingresar a la batidora?",
                        opciones = listOf(
                            "Temperatura ambiente (20 °C)",
                            "Congelada (-10 °C)",
                            "Entre 4 °C y 8 °C (refrigerada por al menos 12 horas)",
                            "Tibia (30 °C)"
                        ),
                        indiceRespuestaCorrecta = 2,
                        explicacion = "Refrigerar entre 4 °C y 8 °C permite que la emulsión atrape aire de forma estable y alcance máximo volumen."
                    ),
                    PreguntaTest(
                        enunciado = "2. ¿Qué proporción del bowl de la batidora se recomienda llenar con crema líquida?",
                        opciones = listOf(
                            "Hasta el borde superior (100%)",
                            "Entre un 20% y un 25% de la capacidad del bowl",
                            "Un 80% de la capacidad del bowl",
                            "Menos del 2%"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Dado que Decor Up aumenta hasta 4 veces su volumen, llenar un 25% permite una aireación libre y homogénea."
                    ),
                    PreguntaTest(
                        enunciado = "3. ¿Qué ventaja técnica ofrece Decor Up frente a una crema láctea tradicional en vitrina?",
                        opciones = listOf(
                            "Mayor estabilidad estructural y cero sinéresis por más horas",
                            "Se hornea a 250 °C sin derretirse",
                            "No necesita batido para montar",
                            "Cambia de color sola con la luz"
                        ),
                        indiceRespuestaCorrecta = 0,
                        explicacion = "Su formulación evita que la decoración se agriete o suelte suero en exhibición prolongada."
                    ),
                    PreguntaTest(
                        enunciado = "4. ¿Cuál es la velocidad recomendada para el batido del producto?",
                        opciones = listOf(
                            "Velocidad máxima desde el primer segundo hasta cortar la crema",
                            "Velocidad media constante hasta obtener picos firmes y brillantes",
                            "Amasado lento con gancho panadero",
                            "Pulso intermitente cada 5 minutos"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "La velocidad media genera microburbujas uniformes que dan suavidad para manguear y espatular."
                    ),
                    PreguntaTest(
                        enunciado = "5. ¿Se puede mezclar Decor Up con coberturas de chocolate o pastas aromatizantes?",
                        opciones = listOf(
                            "No, es incompatible con cualquier ingrediente",
                            "Sí, es ideal para ganaches montadas, mousses y cremas saborizadas",
                            "Solo si se hierve durante 40 minutos",
                            "Únicamente con sal gruesa"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Acepta perfectamente ácidos cítricos, pastas de frutos secos y chocolates fundidos sin cortarse."
                    )
                )
            ),
            Receta(
                id = "bolleria_masas",
                categoria = "Bollería y Masas",
                titulo = "Laminado con Margarina de Hoja",
                descripcionCorta = "Técnica de pliegues simples y dobles para croissants y milhojas crujientes.",
                duracion = "40 min",
                introduccionDetallada = "Bienvenido al módulo de Bollería y Masas Laminadas. " +
                        "El éxito de un croissant alveolado o un milhojas uniforme radica en la plasticidad de la margarina de empaste " +
                        "Master Martini y en mantener la misma consistencia entre el amasijo (masa) y el empaste (materia grasa) " +
                        "a través de pliegues precisos y reposos en frío.",
                youtubeVideoId = "8JxOOw7Wqkw",
                preguntas = listOf(
                    PreguntaTest(
                        enunciado = "1. ¿Cuál es la regla de oro antes de realizar el primer pliegue del empaste?",
                        opciones = listOf(
                            "La masa debe estar caliente y la margarina congelada",
                            "Masa y margarina deben tener la misma plasticidad y firmeza",
                            "La margarina debe estar completamente derretida",
                            "No se debe amasar la harina previamente"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Si tienen igual textura y plasticidad, las capas se estiran de manera continua sin romperse."
                    ),
                    PreguntaTest(
                        enunciado = "2. ¿En cuántas partes se divide visualmente la masa para ejecutar una vuelta o pliegue simple?",
                        opciones = listOf(
                            "En 3 partes iguales (plegado en tríptico)",
                            "En 8 partes asimétricas",
                            "En 2 mitades enrolladas en espiral",
                            "En 10 tiras trenzadas"
                        ),
                        indiceRespuestaCorrecta = 0,
                        explicacion = "El pliegue simple dobla un tercio hacia el centro y el otro tercio encima, triplicando las capas."
                    ),
                    PreguntaTest(
                        enunciado = "3. ¿Por qué es fundamental respetar el reposo en frío entre cada vuelta de laminado?",
                        opciones = listOf(
                            "Para relajar la red de gluten y estabilizar la temperatura de la materia grasa",
                            "Para que la levadura muera por completo",
                            "Para secar la superficie hasta que se quiebre",
                            "Para ahorrar energía en el horno"
                        ),
                        indiceRespuestaCorrecta = 0,
                        explicacion = "El frío relaja la tensión del gluten evitando que la masa se encoja y mantiene intactas las láminas de grasa."
                    ),
                    PreguntaTest(
                        enunciado = "4. ¿Qué provoca el levantamiento físico de las hojas (hojaldrado) dentro del horno?",
                        opciones = listOf(
                            "El polvo de hornear agregado al final",
                            "El vapor de agua liberado por la masa que empuja las capas impermeables de grasa",
                            "El azúcar caramelizado en la bandeja",
                            "La corriente eléctrica del ventilador"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "El agua de la masa se transforma en vapor al hornear y queda retenido por las finas películas de margarina."
                    ),
                    PreguntaTest(
                        enunciado = "5. ¿Qué ocurre si se lamina una masa con exceso de temperatura ambiente sin refrigerar?",
                        opciones = listOf(
                            "El hojaldre sube el doble de lo normal",
                            "La materia grasa es absorbida por la masa, perdiendo las capas y obteniendo un pan brioche pesado",
                            "Se vuelve inmune a la humedad",
                            "Se cocina sin necesidad de horno"
                        ),
                        indiceRespuestaCorrecta = 1,
                        explicacion = "Al fundirse la grasa durante el estirado, las capas de masa se fusionan entre sí y desaparece el hojaldrado."
                    )
                )
            )
        )
    }

    fun obtenerCursoPorId(id: String): Receta? {
        return obtenerCursos().find { it.id == id }
    }
}