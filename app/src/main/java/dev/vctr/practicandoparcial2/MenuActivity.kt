package dev.vctr.practicandoparcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "Menu"

        val buttonICM = findViewById<Button>(R.id.buttonIMC)
        val buttonSignos = findViewById<Button>(R.id.SignosButton)
        val buttonGeneraciones = findViewById<Button>(R.id.generacionesButton)
        val buttonRegresar = findViewById<Button>(R.id.regresarButton)


        buttonICM.setOnClickListener {
            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
        }

        buttonSignos.setOnClickListener {
            val intent = Intent(this, SignosActivity::class.java)
            startActivity(intent)
        }

        buttonGeneraciones.setOnClickListener {
            val intent = Intent(this, GeneracionesActivity::class.java)
            startActivity(intent)
        }

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}