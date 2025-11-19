package com.example.escudos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.escudos.databinding.FragmentMostrarEquiposBinding

class Fragment_Mostrar_Equipos : Fragment() {

    private var _binding: FragmentMostrarEquiposBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMostrarEquiposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar los datos que MainActivity nos pasó
        val nombreEquipo = arguments?.getString("nombre_equipo")
        val escudoEquipo = arguments?.getInt("escudo_equipo")

        // Mostrar los datos en las vistas (si existen)
        if (nombreEquipo != null && escudoEquipo != null) {
            binding.txtNombreEquipo.text = nombreEquipo
            binding.imgEscudo.setImageResource(escudoEquipo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}