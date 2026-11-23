package com.example.grimriodehogwarts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CriaturaActivity : AppCompatActivity() {

    private lateinit var etNomeCriatura: EditText
    private lateinit var btnBuscarCriatura: Button
    private lateinit var progressCriatura: ProgressBar
    private lateinit var tvNomeCriatura: TextView
    private lateinit var ivCriatura: ImageView
    private lateinit var tvStatusImagem: TextView
    private lateinit var tvMensagemCriatura: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criatura)

        etNomeCriatura = findViewById(R.id.etNomeCriatura)
        btnBuscarCriatura = findViewById(R.id.btnBuscarCriatura)
        progressCriatura = findViewById(R.id.progressCriatura)
        tvNomeCriatura = findViewById(R.id.tvNomeCriatura)
        ivCriatura = findViewById(R.id.ivCriatura)
        tvStatusImagem = findViewById(R.id.tvStatusImagem)
        tvMensagemCriatura = findViewById(R.id.tvMensagemCriatura)

        btnBuscarCriatura.setOnClickListener {
            val nome = validarEntrada(etNomeCriatura) ?: return@setOnClickListener
            buscarCriatura(nome)
        }
    }

    private fun buscarCriatura(nomeDigitado: String) {
        limparTela()
        progressCriatura.visibility = View.VISIBLE
        btnBuscarCriatura.isEnabled = false

        lifecycleScope.launch {
            try {
                val resposta = RetrofitClient.api.buscarCriaturas(nomeDigitado)
                val criatura = resposta.data?.firstOrNull { item ->
                    item.name?.trim().equals(nomeDigitado, ignoreCase = true)
                }

                if (criatura == null) {
                    tvMensagemCriatura.text = getString(R.string.nenhuma_criatura_encontrada)
                    finalizarCarregamento()
                    return@launch
                }

                tvNomeCriatura.text = criatura.name.ouNaoInformado()
                val urlImagem = criatura.image?.trim()

                if (urlImagem.isNullOrEmpty()) {
                    exibirImagemIndisponivel()
                    finalizarCarregamento()
                } else {
                    ivCriatura.visibility = View.VISIBLE
                    Picasso.get()
                        .load(urlImagem)
                        .into(ivCriatura, object : Callback {
                            override fun onSuccess() {
                                tvStatusImagem.visibility = View.GONE
                                finalizarCarregamento()
                            }

                            override fun onError(e: Exception?) {
                                exibirImagemIndisponivel()
                                finalizarCarregamento()
                            }
                        })
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    tvMensagemCriatura.text = getString(R.string.nenhuma_criatura_encontrada)
                } else {
                    tvMensagemCriatura.text = getString(R.string.erro_servidor)
                }
                finalizarCarregamento()
            } catch (e: Exception) {
                tvMensagemCriatura.text = getString(R.string.erro_conexao)
                finalizarCarregamento()
            }
        }
    }

    private fun exibirImagemIndisponivel() {
        ivCriatura.setImageResource(R.drawable.ic_imagem_indisponivel)
        ivCriatura.visibility = View.VISIBLE
        tvStatusImagem.text = getString(R.string.imagem_indisponivel)
        tvStatusImagem.visibility = View.VISIBLE
    }

    private fun finalizarCarregamento() {
        progressCriatura.visibility = View.GONE
        btnBuscarCriatura.isEnabled = true
    }

    private fun limparTela() {
        tvNomeCriatura.text = ""
        tvStatusImagem.text = ""
        tvStatusImagem.visibility = View.GONE
        tvMensagemCriatura.text = ""
        ivCriatura.setImageDrawable(null)
        ivCriatura.visibility = View.GONE
    }
}