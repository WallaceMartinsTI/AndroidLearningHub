package com.wcsm.androidlearninghub.guide_activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacoes: Double,
    val diretor: String,
    val distribuidor: String
) : Parcelable

/*
// SERIALIZABLE
data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacoes: Double,
    val diretor: String,
    val distribuidor: String
) : Serializable
*/
