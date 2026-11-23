package com.example.grimoriodehogwarts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.grimriodehogwarts.CriaturaActivity
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPersonagem: Button = findViewById(R.id.btnPersonagem)
        val btnCriatura: Button = findViewById(R.id.btnCriatura)

        btnPersonagem.setOnClickListener {
            startActivity(Intent(this, PersonagemActivity::class.java))
        }

        btnCriatura.setOnClickListener {
            startActivity(Intent(this, CriaturaActivity::class.java))
        }
    }
}