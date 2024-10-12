package dev.vctr.practicandoparcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val regresarIMCButton = findViewById<Button>(R.id.ButtonRegresarMenuIMC)
        title = "IMC"

        regresarIMCButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val peso = findViewById<EditText>(R.id.pesoText)
        val altura = findViewById<EditText>(R.id.alturaText)
        val salida = findViewById<TextView>(R.id.salidaText)


        val calcularIMCButton = findViewById<Button>(R.id.buttonCalcularIMC)
        calcularIMCButton.setOnClickListener {
            if(peso.text.isNotEmpty() && altura.text.isNotEmpty()){
                try {
                    val pesoString = peso.text.toString().toDouble()
                    val alturaString = altura.text.toString().toDouble()

                    val imc = pesoString / (alturaString * alturaString)

                    // Clasificación del IMC
                    val categoriaIMC = when {
                        imc < 18.5 -> "Bajo peso"
                        imc in 18.5..24.9 -> "Peso normal"
                        imc in 25.0..29.9 -> "Sobrepeso"
                        imc in 30.0..34.9 -> "Obesidad grado 1"
                        imc in 35.0..39.9 -> "Obesidad grado 2"
                        imc >= 40.0 -> "Obesidad grado 3"
                        else -> "IMC fuera de rango"
                    }

                    // Mostrar el IMC y la categoría
                    val imcFormatted = String.format("%.2f", imc)
                    salida.text = "IMC: $imcFormatted ($categoriaIMC)"

                }catch (e:NumberFormatException){
                    salida.text = "Ingrese un numero valido"
                }
            }else{
                salida.text = "Ingrese un numero valido"
            }

        }

    }
}