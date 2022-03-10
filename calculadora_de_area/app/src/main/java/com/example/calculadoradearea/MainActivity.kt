package com.example.calculadoradearea

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnTelaQua : Button
    private lateinit var btnTelaRet : Button
    private lateinit var btnTelaTri : Button
    private lateinit var btnTelaCir : Button
    private lateinit var btnInfo : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTelaQua = findViewById(R.id.btnQuadrado)
            btnTelaQua.setOnClickListener{telaQuadrado()}

        btnTelaRet = findViewById(R.id.btnRetangulo)
            btnTelaRet.setOnClickListener{telaRetangulo()}

        btnTelaTri = findViewById(R.id.btnTriangulo)
            btnTelaTri.setOnClickListener{telaTriangulo()}

        btnTelaCir = findViewById(R.id.btnCirculo)
            btnTelaCir.setOnClickListener{telaCirculo()}

        btnInfo = findViewById(R.id.infoApp)
            btnInfo.setOnClickListener{info()}
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Aplicativo para cálculo da área de diferentes formas geométricas. \n\nSelecione abaixo na lista qual forma você deseja calcular a área.")
        info.setPositiveButton("Entendi!"){ _, _ ->  }

        val dialogo: AlertDialog = info.create()
        dialogo.show()
    }

    private fun telaQuadrado(){
        val intent = Intent(this, AreaQuaActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun telaRetangulo(){
        val intent = Intent(this, AreaRetActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun telaTriangulo(){
        val intent = Intent(this, AreaTriActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun telaCirculo(){
        val intent = Intent(this, AreaCrcActivity::class.java).apply {
        }
        startActivity(intent)
    }

}