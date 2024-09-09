package com.wcsm.androidlearninghub.interface_components

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityInterfaceComponentsBinding

class InterfaceComponentsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInterfaceComponentsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            btnEnviar.setOnClickListener {
                //checkbox()
                //radioButton()
                //switchToggle()
                exibirSnackBar(it)
            }
        }
    }

    private fun switchToggle() {
        val switchMarcado = binding.switchNotificacoes.isChecked
        val toggleMarcado = binding.toggleAtivo.isChecked

        val texto = "Switch: $switchMarcado Toggle: $toggleMarcado"
        binding.textResultado.text = texto
    }

    private fun checkbox() {
        // Checkbox
        binding.cbConfirmacao.setOnCheckedChangeListener { _, isChecked ->
            val resultado = if(isChecked) "Sim" else "Não"
            binding.textResultado.text = "Valor selecionado: $resultado"
        }

        /*binding.cbConfirmacao.setOnClickListener {
            val selecionado = binding.cbConfirmacao.isChecked
            val resultado = if(selecionado) "Sim" else "Não"
            binding.textResultado.text = "Valor selecionado: $resultado"
        }*/
    }

    private fun radioButton() {
        // RadioButton
        val masculino = binding.rbMasculino.isChecked
        binding.textResultado.text = if(masculino) "Masculino" else "Feminino"

        val idItemSelecionado = binding.rgSexo.checkedRadioButtonId
        binding.textResultado.text = when(idItemSelecionado) {
            R.id.rbMasculino -> "Masculino"
            R.id.rbFeminino -> "Feminino"
            else -> "Nada selecionado"
        }

        binding.rgSexo.clearCheck()
    }

    private fun exibirSnackBar(view: View) {
        val snackbar = Snackbar.make(
            view,
            "Alteração feita com sucesso!",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Desfazer") {
            Toast.makeText(this, "Desfeito", Toast.LENGTH_SHORT).show()
        }

        /*
        // CORES CUSTOM
        snackbar.setTextColor(
            ContextCompat.getColor(this, android.R.color.holo_green_light)
        )

        snackbar.setActionTextColor(
            ContextCompat.getColor(this, android.R.color.holo_green_dark)
        )

        snackbar.setBackgroundTint(
            ContextCompat.getColor(this, android.R.color.holo_orange_dark)
        )
        */
        snackbar.show()
    }
}