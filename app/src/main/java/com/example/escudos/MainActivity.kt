package com.example.escudos

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// Modelo de datos: cada equipo tendrá un nombre y un escudo (imagen)
data class Equipo(val nombre: String, val imagen: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a vistas del layout principal
        val spinnerEquipos = findViewById<Spinner>(R.id.spinnerEquipos)
        val txtSeleccion = findViewById<TextView>(R.id.txtSeleccion)

        // Cargamos nombres desde arrays.xml
        val nombresEquipos = resources.getStringArray(R.array.equipos_nombres)

        // Cargamos imágenes desde drawable
        val imagenesEquipos = intArrayOf(
            R.drawable.fcbarcelona,
            R.drawable.rmadrid,
            R.drawable.atmadrid,
            R.drawable.rvalladolid
        )

        // Creamos la lista de objetos Equipo combinando nombre + imagen
        val listaEquipos = nombresEquipos.mapIndexed { index, nombre ->
            Equipo(nombre, imagenesEquipos[index])
        }

        // Creamos e instalamos el adaptador personalizado
        val adaptador = object : ArrayAdapter<Equipo>(
            this,
            R.layout.spinner_item,
            listaEquipos
        ) {
            // Método que define cómo se ve cada elemento del Spinner (lista desplegable)
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return crearVistaPersonalizada(position, convertView, parent)
            }

            // Método que define cómo se ven los elementos en la lista cuando el usuario la despliega
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                return crearVistaPersonalizada(position, convertView, parent)
            }

            // Crea una vista con la imagen y el texto combinados
            private fun crearVistaPersonalizada(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.spinner_item, parent, false)
                val equipo = listaEquipos[position]

                val imgEquipo = view.findViewById<ImageView>(R.id.imgEquipo)
                val txtEquipo = view.findViewById<TextView>(R.id.txtEquipo)

                imgEquipo.setImageResource(equipo.imagen)
                txtEquipo.text = equipo.nombre

                return view
            }
        }

        // Asignamos el adaptador al Spinner
        spinnerEquipos.adapter = adaptador

        // Evento: cuando el usuario selecciona un equipo del spinner
        spinnerEquipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Obtenemos el equipo seleccionado y actualizamos el TextView
                val seleccionado = listaEquipos[position]
                txtSeleccion.text = "Has seleccionado: ${seleccionado.nombre}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Si no se selecciona nada (raro, pero posible)
                txtSeleccion.text = getString(R.string.selecciona_equipo)
            }
        }
    }
}