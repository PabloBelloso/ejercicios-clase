package com.example.escudos

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), OnEquipoSeleccionadoListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar la Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Solo cargar fragments la primera vez (no cuando rote la pantalla)
        if (savedInstanceState == null) {
            cargarFragments()
        } else {
            // Recuperar la orientación que guardaste
            val orientracionGuardada = savedInstanceState.getInt("orientacion")

            // Obtener la orientación actual
            val orientationActual = resources.configuration.orientation

            // Comparar: si cambiaron, recargar fragments
            if(orientationActual != orientracionGuardada){
                cargarFragments()
            }
        }
    }

    private fun cargarFragments() {

        android.util.Log.d("MainActivity", "cargarFragments ejecutado")
        // Verificar si estamos en horizontal o vertical
        val esHorizontal = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (esHorizontal) {
            // HORIZONTAL: cargar los dos fragments
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerLista, Fragmen_Lista_equipos())
                .replace(R.id.fragmentContainerMostrar, Fragment_Mostrar_Equipos())
                .commit()
        } else {
            // VERTICAL: cargar solo Fragment Lista
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Fragmen_Lista_equipos())
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val orientacion = resources.configuration.orientation
        outState.putInt("orientacion",orientacion)
    }

    override fun onEquipoSeleccionado(equipo: Equipo) {

        val fragment = Fragment_Mostrar_Equipos()
        val bundle = Bundle()
        bundle.putString("nombre_equipo", equipo.nombre)
        bundle.putInt("escudo_equipo", equipo.escudo)
        fragment.arguments = bundle

        // 2. Detectar orientación
        val esHorizontal = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        // 3. Mostrar el fragment en el contenedor correspondiente
        if(esHorizontal){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMostrar, fragment)
                .commit()
        } else{
            // VERTICAL: reemplazar en el contenedor único
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,fragment)
                .addToBackStack("detalle")
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_tema_paises -> {
                aplicarTemaColores()
                true
            }
            R.id.menu_tema_negro -> {
                aplicarTemaNegro()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun aplicarTemaColores() {
        // Cambiar colores de la interfaz
        window.decorView.setBackgroundColor(getColor(android.R.color.white))
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
            .setBackgroundColor(getColor(android.R.color.holo_blue_dark))
    }

    private fun aplicarTemaNegro() {
        // Cambiar todo a negro
        window.decorView.setBackgroundColor(getColor(android.R.color.black))
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
            .setBackgroundColor(getColor(android.R.color.black))
    }
}