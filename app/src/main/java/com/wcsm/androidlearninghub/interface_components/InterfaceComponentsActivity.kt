package com.wcsm.androidlearninghub.interface_components

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

        criarSpinner()

        with(binding) {
            btnEnviar.setOnClickListener {
                //checkbox()
                //radioButton()
                //switchToggle()
                //exibirSnackBar(it)
                caixaDialogoAlerta()
                //spinnerSelecionarItem()
            }

            btnGoToActionbarActivity.setOnClickListener {
                startActivity(Intent(applicationContext, ActionbarActivity::class.java))
            }

            btnGoToToolbarActivity .setOnClickListener {
                startActivity(Intent(applicationContext, ToolbarActivity::class.java))
            }
        }
    }

    private fun spinnerSelecionarItem() {
        val itemSelecionado = binding.spinnerCategorias.selectedItem
        val itemPosicao = binding.spinnerCategorias.selectedItemPosition
        val itemId = binding.spinnerCategorias.selectedItemId

        if(itemPosicao == 0) {
            binding.textResultado.text = "selecione um item"
        } else {
            binding.textResultado.text = "selecionado: $itemSelecionado - pos: $itemPosicao - id: $itemId"
        }
    }

    private fun criarSpinner() {
        //1val categorias = listOf("Selecione uma categoria", "Eletrônicos", "Roupas", "Móveis")

        /*
        // Buscando um array de strings do arquivo strings.xml
        val categorias = resources.getStringArray(R.array.categorias)

        binding.spinnerCategorias.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categorias
        )*/

        // Forma Compacta
        binding.spinnerCategorias.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerCategorias.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //val itemSelecionado = parent?.getItemAtPosition(position)
                val itemSelecionado = parent?.selectedItem
                binding.textResultado.text = itemSelecionado.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun caixaDialogoAlerta() {
        // FORMA COMPACTA
        AlertDialog.Builder(this)
            .setTitle("Confirmar exclusão do item?")
            .setTitle("Confirmar exclusão do item?")
            .setNegativeButton("cancelar") { dialog, posicao ->
                Toast.makeText(this, "Cancelar ($posicao)", Toast.LENGTH_SHORT).show()
                //dialog.dismiss()
            }
            .setPositiveButton("remover") { dialog, posicao ->
                Toast.makeText(this, "Remover ($posicao)", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("ajuda") { dialog, posicao ->
                Toast.makeText(this, "Ajuda ($posicao)", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()


        /* CONFIGURAÇÃO ISOLADA
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão do item?")
        alertBuilder.setMessage("Tem certeza que deseja remover?")
        alertBuilder.setNegativeButton("cancelar") { dialog, posicao ->
            Toast.makeText(this, "Cancelar ($posicao)", Toast.LENGTH_SHORT).show()
            //dialog.dismiss()
        }
        alertBuilder.setPositiveButton("remover") { dialog, posicao ->
            Toast.makeText(this, "Remover ($posicao)", Toast.LENGTH_SHORT).show()
        }
        alertBuilder.setNeutralButton("ajuda") { dialog, posicao ->
            Toast.makeText(this, "Ajuda ($posicao)", Toast.LENGTH_SHORT).show()
        }

        // true = padrão, usuario pode cancelar clicando fora da caixa
        // false = usuario deve escolher uma opção
        alertBuilder.setCancelable(true)

        alertBuilder.setIcon(R.drawable.ic_warning_24)

        val alertDialog = alertBuilder.create()
        alertDialog.show()
        */
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
}