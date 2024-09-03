package com.wcsm.androidlearninghub.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wcsm.androidlearninghub.R

class MensagemAdapter(
    private val clique: (String) -> Unit
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    private var listamensagens = mutableListOf<Mensagem>()

    fun atualizarListaDados(lista: MutableList<Mensagem>) {
        listamensagens = lista
        notifyDataSetChanged()
    }

    inner class MensagemViewHolder(
        val itemView: View
    ) : ViewHolder(itemView) {
        //val textNome: TextView = itemView.findViewById(R.id.text_rv_nome)
        //val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
        //val textHorario: TextView = itemView.findViewById(R.id.text_horario)

        val textNome: TextView = itemView.findViewById(R.id.text_card_nome)
        val textUltima: TextView = itemView.findViewById(R.id.text_card_ultima)
        val imagePerfil: ImageView = itemView.findViewById(R.id.image_rv_perfil)
        val cardView: CardView = itemView.findViewById(R.id.card_view_layout)

        fun bind(mensagem: Mensagem) {
            textNome.text = mensagem.nome
            textUltima.text = mensagem.ultima

            cardView.setOnClickListener {
                clique( mensagem.nome )
                /*Toast.makeText(
                    it.context,
                    "Clicou Card View de: ${mensagem.nome}",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            //holder.textHorario.text = mensagem.horario

            // Aplicar eventos de clique
            //val context = holder.imagePerfil.context
            //holder.imagePerfil.setOnClickListener {
            //    Toast.makeText(context, "Olá ${mensagem.nome}", Toast.LENGTH_SHORT).show()
            //}
        }

    }

    // Ao vincular os dados para a visualização
    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = listamensagens[position]
        holder.bind(mensagem)
    }

    // Ao criar o View Holder -> Criar a visualização
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //val itemView = layoutInflater.inflate(R.layout.item_lista, parent, false)
        val itemView = layoutInflater.inflate(R.layout.item_cardview, parent, false)
        return MensagemViewHolder(itemView)
    }

    // getItemCount -> Recuperar a quantidade
    override fun getItemCount(): Int {
        return listamensagens.size
    }

}