package com.wcsm.androidlearninghub.content_fragment.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.wcsm.androidlearninghub.R

//class ConversasFragment : Fragment(R.layout.fragment_conversas) { -> Se não for fazer alterações na tela
class ConversasFragment : Fragment() {

    private lateinit var btnExecutar: Button
    private lateinit var editNome: EditText
    private lateinit var textNome: TextView
    private lateinit var textCategoria: TextView

    private var categoria: String? = null
    private var usuario: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ciclo_vida", "Fragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo_vida", "Fragment onCreate")

        categoria = arguments?.getString("categoria")
        usuario = arguments?.getString("usuario")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ciclo_vida", "Fragment onCreateView")

        val view = inflater.inflate(
            R.layout.fragment_conversas,
            container,
            false
        )

        // Processamento da visualização

        // btnExecutar = view.findViewById(R.id.btn_executar)

        with(view) {
            btnExecutar = findViewById(R.id.btn_executar)
            editNome = findViewById(R.id.edit_nome)
            textNome = findViewById(R.id.text_nome)
            textCategoria = findViewById(R.id.text_categoria)

            textCategoria.text = categoria
            textNome.text = usuario
        }

        btnExecutar.setOnClickListener {
            textNome.text = editNome.text.toString()
        }

        return view
    }

    /* DEPRECIADO
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    } */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ciclo_vida", "Fragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "Fragment onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "Fragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "Fragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "Fragment onStop")
    }

    override fun onDestroyView() {
        Log.i("ciclo_vida", "Fragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("ciclo_vida", "Fragment onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("ciclo_vida", "Fragment onDetach")
        super.onDetach()
    }

}