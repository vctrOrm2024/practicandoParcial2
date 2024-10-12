package dev.vctr.practicandoparcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "login"
        val textCorreo = findViewById<EditText>(R.id.correoText)
        val textContra = findViewById<EditText>(R.id.contraText)
        val buttonIngresar = findViewById<Button>(R.id.buttonIngresar)

        buttonIngresar.setOnClickListener {
            val correo = textCorreo.text.toString()
            val contra = textContra.text.toString()
            if (correo.isNotEmpty() && contra.isNotEmpty()) {
                val correoCorrecto: String = "admin"
                val contraCorrecta: String = "contra"
                if(correo == correoCorrecto && contra == contraCorrecta){
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show()
            }

        }

    }
}