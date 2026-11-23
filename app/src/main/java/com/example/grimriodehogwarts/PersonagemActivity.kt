package com.example.grimoriodehogwarts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.grimriodehogwarts.R
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PersonagemActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var btnBuscar: Button
    private lateinit var progress: ProgressBar
    private lateinit var tvNome: TextView
    private lateinit var tvEspecie: TextView
    private lateinit var tvCasa: TextView
    private lateinit var tvMensagem: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personagem)

        etNome = findViewById(R.id.etNome)
        btnBuscar = findViewById(R.id.btnBuscar)
        progress = findViewById(R.id.progress)
        tvNome = findViewById(R.id.tvNome)
        tvEspecie = findViewById(R.id.tvEspecie)
        tvCasa = findViewById(R.id.tvCasa)
        tvMensagem = findViewById(R.id.tvMensagem)

        btnBuscar.setOnClickListener {
            val nome = validarEntrada(etNome) ?: return@setOnClickListener
            buscar(nome)
        }
    }

    private fun buscar(nome: String) {
        limparTela()
        progress.visibility = View.VISIBLE
        btnBuscar.isEnabled = false

        lifecycleScope.launch {
            try {
                val resposta = RetrofitClient.api.buscarPersonagens(nome)
                val personagem = resposta.data?.firstOrNull()

                if (personagem == null) {
                    tvMensagem.text = "Nenhum personagem encontrado"
                } else {
                    tvNome.text = personagem.name.ouNaoInformado()
                    tvEspecie.text = "Espécie: ${personagem.species.ouNaoInformado()}"
                    tvCasa.text = "Casa: ${personagem.house.ouNaoInformado()}"
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    tvMensagem.text = "Nenhum personagem encontrado"
                } else {
                    tvMensagem.text = "Erro no servidor. Tente novamente em instantes."
                }
            } catch (e: Exception) {
                tvMensagem.text =
                    "Sem conexão ou erro inesperado. Verifique a internet e tente novamente."
            } finally {
                progress.visibility = View.GONE
                btnBuscar.isEnabled = true
            }
        }
    }

    private fun limparTela() {
        tvNome.text = ""
        tvEspecie.text = ""
        tvCasa.text = ""
        tvMensagem.text = ""
    }
}