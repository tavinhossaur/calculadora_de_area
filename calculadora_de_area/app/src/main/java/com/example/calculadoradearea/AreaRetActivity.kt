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

class AreaRetActivity : AppCompatActivity() {

    private lateinit var inputBase1 : EditText
    private lateinit var inputAltura1 : EditText
    private lateinit var resCm2 : EditText
    private lateinit var resM2 : EditText
    private lateinit var textOu2 : TextView
    private lateinit var btnCalc2 : Button
    private lateinit var btnLimpar2 : Button
    private lateinit var btnVoltar2 : ImageButton
    private lateinit var btnInfo2 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retangulo_activity)

        inputBase1 = findViewById(R.id.inputBase1)
        inputAltura1 = findViewById(R.id.inputAltura1)
        resCm2 = findViewById(R.id.outputRetCm)
        resM2 = findViewById(R.id.outputRetM)
        textOu2 = findViewById(R.id.textOu2)

        btnCalc2 = findViewById(R.id.btnCalcRet)
            btnCalc2.setOnClickListener{calcularAreaRetangulo()}

        btnLimpar2 = findViewById(R.id.btnLimparRet)
            btnLimpar2.setOnClickListener{limpar()}

        btnVoltar2 = findViewById(R.id.btnVoltar2)
            btnVoltar2.setOnClickListener{voltarAoMenu()}

        btnInfo2 = findViewById(R.id.infoRet)
            btnInfo2.setOnClickListener{info()}
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Calcule a área do retângulo!. \n\nA fórmula utilizada para o cálculo é A = base x altura \n\n(A = b.h)")
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
        inputBase1.setText("")
        inputAltura1.setText("")
        resCm2.setText("")
        resM2.setText("")
        textOu2.text = ""
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
    private fun calcularAreaRetangulo(){

        if (inputBase1.length() == 0 || inputAltura1.length() == 0){
                inputBase1.setHint("Insira valores válidos!")
                inputAltura1.setHint("Insira valores válidos!")
                resCm2.setText("Erro!")
                    resCm2.setTextColor(ContextCompat.getColor(this, R.color.red))
                resM2.setText("Erro!")
                    resM2.setTextColor(ContextCompat.getColor(this, R.color.red))
                esconderTeclado()
        }else{
                val base = inputBase1.text.toString().toFloat()
                val altura = inputAltura1.text.toString().toFloat()
                val formulaAreaRetCm = base * altura
                val formulaAreaRetM = base * altura / 10000

            resCm2.setText("$formulaAreaRetCm cm²")
            textOu2.text = "ou"
            resM2.setText("$formulaAreaRetM m²")

            esconderTeclado()
        }
    }
}