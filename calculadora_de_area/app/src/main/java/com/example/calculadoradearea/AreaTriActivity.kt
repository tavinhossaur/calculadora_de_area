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

class AreaTriActivity : AppCompatActivity() {

    private lateinit var inputBase2 : EditText
    private lateinit var inputAltura2 : EditText
    private lateinit var resCm3 : EditText
    private lateinit var resM3 : EditText
    private lateinit var textOu3 : TextView
    private lateinit var btnCalc3 : Button
    private lateinit var btnLimpar3 : Button
    private lateinit var btnVoltar3 : ImageButton
    private lateinit var btnInfo3 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.triangulo_activity)

        inputBase2 = findViewById(R.id.inputBase2)
        inputAltura2 = findViewById(R.id.inputAltura2)
        resCm3 = findViewById(R.id.outputTriCm)
        resM3 = findViewById(R.id.outputTriM)
        textOu3 = findViewById(R.id.textOu3)

        btnCalc3 = findViewById(R.id.btnCalcTri)
            btnCalc3.setOnClickListener{calcularAreaTriangulo()}

        btnLimpar3 = findViewById(R.id.btnLimparTri)
            btnLimpar3.setOnClickListener{limpar()}

        btnVoltar3 = findViewById(R.id.btnVoltar3)
            btnVoltar3.setOnClickListener{voltarAoMenu()}

        btnInfo3 = findViewById(R.id.infoTri)
            btnInfo3.setOnClickListener{info()}
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Calcule a área do triângulo!. \n\nA fórmula utilizada para o cálculo é A = base x altura / 2 \n\n(A = b.h / 2)")
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
        inputBase2.setText("")
        inputAltura2.setText("")
        resCm3.setText("")
        resM3.setText("")
        textOu3.text = ""
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
    private fun calcularAreaTriangulo(){

        if (inputBase2.length() == 0 || inputAltura2.length() == 0) {
                inputBase2.setHint("Insira valores válidos!")
                inputAltura2.setHint("Insira valores válidos!")
                resCm3.setText("Erro!")
                    resCm3.setTextColor(ContextCompat.getColor(this, R.color.red))
                resM3.setText("Erro!")
                    resM3.setTextColor(ContextCompat.getColor(this, R.color.red))
                esconderTeclado()
        }else{
                val base = inputBase2.text.toString().toFloat()
                val altura = inputAltura2.text.toString().toFloat()
                val formulaAreaTriCm = base * altura / 2
                val formulaAreaTriM = base * altura / 2 / 10000 // ((b*h) / 2) / 10000

            resCm3.setText("$formulaAreaTriCm cm²")
            textOu3.text = "ou"
            resM3.setText("$formulaAreaTriM m²")

            esconderTeclado()
        }
    }
}