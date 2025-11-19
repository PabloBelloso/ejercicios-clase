package com.example.escudos

// Data class que representa un equipo de fútbol
data class Equipo(
    val nombre: String,      // Nombre del equipo
    val escudo: Int,         // ID del recurso drawable (imagen)
    var habilitado: Boolean = true  // Para el menú contextual (habilitar/deshabilitar)
)

// Objeto que contiene la lista de todos los equipos
object EquiposData {
    val equipos = listOf(
        Equipo("Real Valladolid", R.drawable.rvalladolid),
        Equipo("F.C Barcelona", R.drawable.fcbarcelona),
        Equipo("Real Madrid", R.drawable.rmadrid),
        Equipo("At. Madrid", R.drawable.atmadrid)
    )
}