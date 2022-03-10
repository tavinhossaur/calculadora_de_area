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

class AreaQuaActivity : AppCompatActivity() {

    private lateinit var inputLado : EditText
    private lateinit var resCm1 : EditText
    private lateinit var resM1 : EditText
    private lateinit var textOu1 : TextView
    private lateinit var btnCalc1 : Button
    private lateinit var btnLimpar1 : Button
    private lateinit var btnVoltar1 : ImageButton
    private lateinit var btnInfo1 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quadrado_activity)

        inputLado = findViewById(R.id.inputLado)
        resCm1 = findViewById(R.id.outputQuaCm)
        resM1 = findViewById(R.id.outputQuaM)
        textOu1 = findViewById(R.id.textOu1)

        btnCalc1 = findViewById(R.id.btnCalcQua)
            btnCalc1.setOnClickListener{calcularAreaQuadrado()}

        btnLimpar1 = findViewById(R.id.btnLimparQua)
            btnLimpar1.setOnClickListener{limpar()}

        btnVoltar1 = findViewById(R.id.btnVoltar1)
            btnVoltar1.setOnClickListener{voltarAoMenu()}

        btnInfo1 = findViewById(R.id.infoQua)
            btnInfo1.setOnClickListener{info()}
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Calcule a área do quadrado!. \n\nA fórmula utilizada para o cálculo é A = lado x lado \n\n(lado²)")
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
        inputLado.setText("")
        resCm1.setText("")
        resM1.setText("")
        textOu1.text = ""
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
    private fun calcularAreaQuadrado(){

        if (inputLado.length() == 0){
                inputLado.setHint("Insira um valor válido!")
                resCm1.setText("Erro!")
                    resCm1.setTextColor(ContextCompat.getColor(this, R.color.red))
                resM1.setText("Erro!")
                    resM1.setTextColor(ContextCompat.getColor(this, R.color.red))
                esconderTeclado()
        }else{
                val lado = inputLado.text.toString().toFloat()
                val formulaAreaQuaCm = lado*lado
                val formulaAreaQuaM = lado*lado / 10000

            resCm1.setText("$formulaAreaQuaCm cm²")
            textOu1.text = "ou"
            resM1.setText("$formulaAreaQuaM m²")

            esconderTeclado()
        }
    }
}