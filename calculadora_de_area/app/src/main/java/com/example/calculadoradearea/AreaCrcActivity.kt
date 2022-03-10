package com.example.calculadoradearea

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class AreaCrcActivity : AppCompatActivity() {

    private lateinit var inputRaio : EditText
    private lateinit var resCm4 : EditText
    private lateinit var resM4 : EditText
    private lateinit var textOu4 : TextView
    private lateinit var btnCalc4 : Button
    private lateinit var btnLimpar4 : Button
    private lateinit var btnVoltar4 : ImageButton
    private lateinit var btnInfo4 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circulo_activity)

        inputRaio = findViewById(R.id.inputRaio)
        resCm4 = findViewById(R.id.outputCirCm)
        resM4 = findViewById(R.id.outputCirM)
        textOu4 = findViewById(R.id.textOu4)

        btnCalc4 = findViewById(R.id.btnCalcCir)
        btnCalc4.setOnClickListener{calcularAreaCirculo()}

        btnLimpar4 = findViewById(R.id.btnLimparCir)
        btnLimpar4.setOnClickListener{limpar()}

        btnVoltar4 = findViewById(R.id.btnVoltar4)
        btnVoltar4.setOnClickListener{voltarAoMenu()}

        btnInfo4 = findViewById(R.id.infoCir)
        btnInfo4.setOnClickListener{info()}
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Calcule a área do círculo!. \n\nA fórmula utilizada para o cálculo é A = 3,14 x raio x raio \n\n(A = π.r) \n\nπ(pi)")
        info.setPositiveButton("Entendi!"){ _, _ ->  }

        val dialogo: AlertDialog = info.create()
        dialogo.show()
    }

    private fun voltarAoMenu(){
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun limpar(){
        inputRaio.setText("")
        resCm4.setText("")
        resM4.setText("")
        textOu4.text = ""
    }

    fun Fragment.esconderTeclado() {
        view?.let { activity?.esconderTeclado(it) }
    }

    fun Activity.esconderTeclado() {
        if (currentFocus == null) View(this) else currentFocus?.let { esconderTeclado(it) }
    }

    fun Context.esconderTeclado(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @SuppressLint("SetTextI18n")
    private fun calcularAreaCirculo(){
        val pi = 3.14F

        if (inputRaio.length() == 0){
                inputRaio.setHint("Insira um valor válido!")
                resCm4.setText("Erro!")
                    resCm4.setTextColor(ContextCompat.getColor(this, R.color.red))
                resM4.setText("Erro!")
                    resM4.setTextColor(ContextCompat.getColor(this, R.color.red))
                esconderTeclado()
        }else{
                val raio = inputRaio.text.toString().toFloat()
                val formulaAreaCirCm = pi * raio * raio
                val formulaAreaCirM = pi * raio * raio / 10000

            resCm4.setText("$formulaAreaCirCm cm²")
            textOu4.text = "ou"
            resM4.setText("$formulaAreaCirM m²")

            esconderTeclado()
        }
    }
}