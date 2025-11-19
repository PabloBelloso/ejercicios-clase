package com.example.escudos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class EquipoAdapter(
    context: Context,
    private val equipos: List<Equipo>
) : ArrayAdapter<Equipo>(context, R.layout.spinner_item, equipos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Reutilizar la vista si ya existe (optimización)
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)

        // Obtener el equipo de esta posición
        val equipo = equipos[position]

        // Encontrar las vistas del layout
        val imgEquipo = view.findViewById<ImageView>(R.id.imgEquipo)
        val txtEquipo = view.findViewById<TextView>(R.id.txtEquipo)

        // Mostrar el equipo solo si está habilitado
        if (equipo.habilitado) {
            imgEquipo.setImageResource(equipo.escudo)
            txtEquipo.text = equipo.nombre
            view.alpha = 1.0f // Completamente visible
        } else {
            // Si está deshabilitado, mostrarlo gris/transparente
            imgEquipo.setImageResource(equipo.escudo)
            txtEquipo.text = equipo.nombre
            view.alpha = 0.5f // Medio transparente
        }

        return view
    }
}