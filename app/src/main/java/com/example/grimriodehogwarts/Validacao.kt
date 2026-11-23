package com.example.grimriodehogwarts

import android.widget.EditText

fun String?.ouNaoInformado(): String =
    if (this.isNullOrBlank()) "Não informado" else this

fun validarEntrada(campo: EditText): String? {
    val texto = campo.text.toString().trim()

    if (texto.isEmpty()) {
        campo.error = "Digite um nome para buscar"
        return null
    }
    if (texto.length < 3) {
        campo.error = "Digite pelo menos 3 caracteres"
        return null
    }
    campo.error = null
    return texto
}