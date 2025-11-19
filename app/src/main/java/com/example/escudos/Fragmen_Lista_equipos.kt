package com.example.escudos

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.escudos.databinding.FragmentFragmenListaEquiposBinding

class Fragmen_Lista_equipos : Fragment() {

    // ViewBinding para acceder a las vistas del layout
    private var _binding: FragmentFragmenListaEquiposBinding? = null
    private val binding get() = _binding!!

    // AÑADE ESTA LÍNEA:
    private var listener: OnEquipoSeleccionadoListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        android.util.Log.d("FragmentLista", "onCreateView ejecutado")
        _binding = FragmentFragmenListaEquiposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Obtener la Activity y convertirla al tipo OnEquipoSeleccionadoListener
        listener = context as? OnEquipoSeleccionadoListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        android.util.Log.d("FragmentLista", "onViewCreated ejecutado")
        configurarListView()
    }

    private fun configurarListView() {
        android.util.Log.d("FragmentLista", "configurarListView iniciado")
        android.util.Log.d("FragmentLista", "Número de equipos: ${EquiposData.equipos.size}")

        val adapter = EquipoAdapter(requireContext(), EquiposData.equipos)
        binding.listViewEquipos.adapter = adapter

        // Registrar el ListView para menús contextuales
        registerForContextMenu(binding.listViewEquipos)  // AÑADE ESTA LÍNEA

        android.util.Log.d("FragmentLista", "Adapter asignado al ListView")

        binding.listViewEquipos.setOnItemClickListener { _, _, position, _ ->
            val equipoSeleccionado = EquiposData.equipos[position]
            android.util.Log.d("FragmentLista", "Equipo clickeado: ${equipoSeleccionado.nombre}")
            listener?.onEquipoSeleccionado(equipoSeleccionado)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu_equipo, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val equipo = EquiposData.equipos[position]

        return when (item.itemId) {
            R.id.menu_deshabilitar -> {
                equipo.habilitado = false
                (binding.listViewEquipos.adapter as EquipoAdapter).notifyDataSetChanged()
                true
            }
            R.id.menu_habilitar -> {
                equipo.habilitado = true
                (binding.listViewEquipos.adapter as EquipoAdapter).notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Interfaz para comunicar la selección de un equipo
interface OnEquipoSeleccionadoListener {
    fun onEquipoSeleccionado(equipo: Equipo)
}