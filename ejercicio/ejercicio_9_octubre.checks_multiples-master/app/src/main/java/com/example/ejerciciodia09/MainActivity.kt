package com.example.ejerciciodia09

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /**
     * Cómo no uso las variables fuera de esta clase las inicializo aquí.
     * Valladolid
     * Zamora
     * Salamanca
     * Segovia
     * Soria
     * León
     * Ávila
     * Burgos
     */

    // Provincias.
    private lateinit var checkVall: CheckBox
    private lateinit var checkZam: CheckBox
    private lateinit var checkSal: CheckBox
    private lateinit var checkSeg: CheckBox
    private lateinit var checkSor: CheckBox
    private lateinit var checkLeo: CheckBox
    private lateinit var checkAvi: CheckBox
    private lateinit var checkBur: CheckBox

    // Texto donde se van a visualizar.
    private lateinit var txtCheck: TextView

    //  onCREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * He decidido hacer las funciones fuera del onCreate, por ello he usado lateinit para las variables.
         * 1º Problema inicializar las vista.
         * 2º Problema configurar los listeners de cada una de los checkBox.
         * 3º Problema actualizar el TextView, comprobando que checkBox esta seleccionado.
         *      Para ello usamos la función ** actualizarTexto ** dentro de la funcion ** configurarListeners **
          */

        // Vinculamos las vistas con los distintos elementos.
        inicializarVistas()

        // Configuramos los listeners
        configurarListeners()

    }

    // Función para la inicialización de los CheckBox y el TextView.
    private fun inicializarVistas(){

        // Usamos el findViewById para enlazar variable y elemento a través del id del elemento.

        checkVall = findViewById(R.id.ck_Val)
        checkZam = findViewById(R.id.ckZam)
        checkSal = findViewById(R.id.ckSal)
        checkSeg = findViewById(R.id.ckSeg)
        checkSor = findViewById(R.id.ckSor)
        checkLeo = findViewById(R.id.ckLeo)
        checkAvi = findViewById(R.id.ckAvi)
        checkBur = findViewById(R.id.ckBur)

        txtCheck = findViewById(R.id.txtSelect)
    }

    // Función para la configuración de los listeners
    private fun configurarListeners(){

        // Aquí el parametro de tipo boolean (segundo parametro) pasamos la funcion actualizarTexto para verificar si esta check o no.

        checkVall.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkZam.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkSal.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkSeg.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkSor.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkLeo.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkAvi.setOnCheckedChangeListener { _, _ -> actualizarTexto() }
        checkBur.setOnCheckedChangeListener { _, _ -> actualizarTexto() }

    }

    // Función para actualizar el TextView

    private fun actualizarTexto(){

        // Necesito una variable auxiliar para poder actualizar luego el TextView

        val citySelect = mutableListOf<String>() // Recordamos que en Kotlin tenemos un tipo de listas mutables ** mutableListOf<tipo>() **

        // De una forma primitiva vamos comprobando con if cada CheckBox. Esto es poco eficiente.

        if (checkVall.isChecked) {
            citySelect.add("Valladolid")
        }
        if (checkZam.isChecked) {
            citySelect.add("Zamora")
        }
        if (checkSal.isChecked) {
            citySelect.add("Salamanca")
        }
        if (checkSeg.isChecked) {
            citySelect.add("Segovia")
        }
        if (checkSor.isChecked) {
            citySelect.add("Soria")
        }
        if (checkLeo.isChecked) {
            citySelect.add("León")
        }
        if (checkAvi.isChecked) {
            citySelect.add("Ávila")
        }
        if (checkBur.isChecked) {
            citySelect.add("Burgos")
        }
        // Actualizar el TextView con el metodo .text de al ser de tipo String
        txtCheck.text = if(citySelect.isEmpty()){
            "" // ninguna ciudad seleccionada
        } else {
            citySelect.joinToString(",") //
        }
    }
}