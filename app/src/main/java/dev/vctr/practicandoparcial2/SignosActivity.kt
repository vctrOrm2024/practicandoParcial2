package dev.vctr.practicandoparcial2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

class SignosActivity : AppCompatActivity() {

    private lateinit var etFechaNacimiento: EditText
    private lateinit var btnConsultar: Button
    private lateinit var tvEdad: TextView
    private lateinit var tvSignoZodiacal: TextView
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signos)

        // Configuración de borde a borde
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "Signos Zodiacales"

        // Referencias a las vistas
        etFechaNacimiento = findViewById(R.id.fechaNacimientoText)
        btnConsultar = findViewById(R.id.calcularSignoButton)
        tvEdad = findViewById(R.id.edadTEXT)
        tvSignoZodiacal = findViewById(R.id.SignoText)
        btnRegresar = findViewById(R.id.buttonRegresarSgno)

        // Mostrar el DatePicker al hacer clic en el campo de fecha
        etFechaNacimiento.setOnClickListener {
            mostrarDatePicker()
        }

        // Calcular edad y signo al presionar el botón de consultar
        btnConsultar.setOnClickListener {
            val fechaNacimiento = etFechaNacimiento.text.toString()
            if (fechaNacimiento.isNotEmpty()) {
                calcularEdadYSigno(fechaNacimiento)
            } else {
                Toast.makeText(this, "Por favor ingrese su fecha de nacimiento", Toast.LENGTH_SHORT).show()
            }
        }

        // Regresar al menú principal (o a otra actividad)
        btnRegresar.setOnClickListener {
            finish()  // Finaliza la actividad actual
        }
    }

    // Mostrar el DatePicker para seleccionar la fecha de nacimiento
    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()
        val year = calendario.get(Calendar.YEAR)
        val month = calendario.get(Calendar.MONTH)
        val day = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
            etFechaNacimiento.setText(fechaSeleccionada)
        }, year, month, day)

        datePickerDialog.show()
    }

    // Función para calcular la edad y el signo zodiacal
    private fun calcularEdadYSigno(fechaNacimiento: String) {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val fecha = formatoFecha.parse(fechaNacimiento)
        val hoy = Calendar.getInstance()

        // Calcular la edad
        val nacimiento = Calendar.getInstance()
        nacimiento.time = fecha
        var edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR)
        if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--
        }
        tvEdad.text = "Edad: $edad"

        // Calcular el signo zodiacal
        val signo = obtenerSignoZodiacal(nacimiento.get(Calendar.DAY_OF_MONTH), nacimiento.get(Calendar.MONTH) + 1)
        tvSignoZodiacal.text = "Signo: $signo"
    }

    // Función para determinar el signo zodiacal basado en la fecha de nacimiento
    private fun obtenerSignoZodiacal(dia: Int, mes: Int): String {
        return when (mes) {
            1 -> if (dia >= 21) "Acuario" else "Capricornio"
            2 -> if (dia >= 20) "Piscis" else "Acuario"
            3 -> if (dia >= 21) "Aries" else "Piscis"
            4 -> if (dia >= 21) "Tauro" else "Aries"
            5 -> if (dia >= 21) "Géminis" else "Tauro"
            6 -> if (dia >= 22) "Cáncer" else "Géminis"
            7 -> if (dia >= 23) "Leo" else "Cáncer"
            8 -> if (dia >= 23) "Virgo" else "Leo"
            9 -> if (dia >= 23) "Libra" else "Virgo"
            10 -> if (dia >= 23) "Escorpio" else "Libra"
            11 -> if (dia >= 23) "Sagitario" else "Escorpio"
            12 -> if (dia >= 22) "Capricornio" else "Sagitario"
            else -> "Desconocido"
        }
    }
}
