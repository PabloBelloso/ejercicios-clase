package com.example.ejerciciospinnercmancha


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // ponerlas en el mismo orden correspondiente provincia-imagen
    private val provincias = arrayOf("Albacete", "Ciudad Real", "Cuenca", "Guadalajara", "Toledo")
    private val imagenes = intArrayOf(
        R.drawable.albacete,
        R.drawable.ciudadreal,
        R.drawable.cuenca,
        R.drawable.guadalajara,
        R.drawable.toledo
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner_provincias)
        val textoSeleccionado = findViewById<TextView>(R.id.textoProvincias)

        val adaptador = object : ArrayAdapter<String>(this, R.layout.item_spinner, provincias) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return crearVistaPersonalizada(position, parent)
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                return crearVistaPersonalizada(position, parent)
            }

            private fun crearVistaPersonalizada(position: Int, parent: ViewGroup): View {

                val vista = layoutInflater.inflate(R.layout.item_spinner, parent, false)

                // Referenciamos los elementos dentro del layout
                val imagen = vista.findViewById<ImageView>(R.id.imgProvincia)
                val texto = vista.findViewById<TextView>(R.id.textoProvincias)

                // aquí asocio las imagenes en cada posicion con su imagen y con su nombre
                imagen.setImageResource(imagenes[position])
                texto.text = provincias[position]

                return vista
            }
        } // llave del adaptador

        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                textoSeleccionado.text = "Provincia seleccionada: ${provincias[position]}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}