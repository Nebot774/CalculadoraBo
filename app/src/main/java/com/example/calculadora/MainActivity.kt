package com.example.calculadora

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {

    private lateinit var textoCalculo: TextView
    private var operacionActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        textoCalculo = findViewById(R.id.textoCalculo)

        val botonesNumericos = listOf<Button>(
            findViewById(R.id.boton1),
            findViewById(R.id.boto2),
            findViewById(R.id.boto3),
            findViewById(R.id.boto4),
            findViewById(R.id.boto5),
            findViewById(R.id.boto6),
            findViewById(R.id.boto7),
            findViewById(R.id.boto8),
            findViewById(R.id.boto9)
        )

        for (boton in botonesNumericos) {
            boton.setOnClickListener {
                numeroPresionado(boton.text.toString())
            }
        }

        val botonSuma = findViewById<Button>(R.id.botoSuma)
        botonSuma.setOnClickListener {
            operacionPresionada("+")
        }

        val botonIgual = findViewById<Button>(R.id.botoIgual)
        botonIgual.setOnClickListener {
            calcularResultado()
        }

        val botonCReset = findViewById<Button>(R.id.botoCReset)
        botonCReset.setOnClickListener {
            reiniciarCalculadora()
        }
    }


    private fun numeroPresionado(numero: String) {
        operacionActual += numero
        actualizarOperacionEnPantalla()
    }

    private fun operacionPresionada(operador: String) {
        operacionActual += " $operador "
        actualizarOperacionEnPantalla()
    }

    private fun actualizarOperacionEnPantalla() {
        textoCalculo.text = operacionActual
    }

    //funcion para calcular el resultado
    private fun calcularResultado(){
        try {
            //variable donde llamamos a la funcion calcular pasandole operacion actual como parametro
            val resultado = calculate(operacionActual)
            //a el a¡text view de texto calculo le pasamos el resultado de la variable anterior en formato sting para mostrarlo por pantalla
            textoCalculo.text = resultado.toString()
        }catch(ex:Exception){
            //si algo sale mal lanzamos excepcion i mostramos en texto calculo error
            textoCalculo.text="Error"
        }


    }

    private fun reiniciarCalculadora(){
        operacionActual=""
        actualizarOperacionEnPantalla()
    }



    //funcion donde calculamos las operaciones pasandole como parametro la string de cadena
    private fun calculate(expression: String): Int {
        val partes = expression.split("+")  // Divide la expresión en partes utilizando el operador "+"

        // Mapea cada parte a un número entero o 0 si no se puede convertir y luego suma las partes
        val resultado = partes.map { it.trim().toIntOrNull() ?: 0 }.sum()

        return resultado
    }



}



