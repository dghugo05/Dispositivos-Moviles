package com.example.repartirpropina

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.repartirpropina.ui.theme.RepartirPropinaTheme
import kotlin.reflect.KMutableProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepartirPropinaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)) {
                        RepartoCuenta()
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    RepartirPropinaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)){
                RepartoCuenta()

            }
        }
    }
}

@Composable
fun RepartoCuenta(){
    var cantidad by remember{ mutableStateOf("") }
    var comensales by remember{mutableStateOf("")}
    var redondear by remember{mutableStateOf(false)}
    var valoracion by remember { mutableFloatStateOf(0f) }
    var porcentaje = 0.00
    var mostrarResultado by remember { mutableStateOf(false) }
    val cantidadDouble = cantidad.toDoubleOrNull() ?: 0.0
    val comensalesInt = comensales.toIntOrNull() ?: 0

    if(valoracion.toInt() == 1){
        porcentaje = 0.05
    }else if(valoracion.toInt() == 2){
        porcentaje = 0.10
    }else if(valoracion.toInt() == 3){
        porcentaje = 0.15
    }else if(valoracion.toInt() == 4){
        porcentaje = 0.20
    }else if (valoracion.toInt() == 5){
        porcentaje = 0.25
    }

    val totalConPropina = if(redondear){
        cantidadDouble + (cantidadDouble * porcentaje)
    }else{
        cantidadDouble
    }

    val porPersona = if(comensalesInt > 0) {
        totalConPropina / comensalesInt
    }else{
        0.0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        // Cantidad
        OutlinedTextField(
            value = stringResource(R.string.Quantity),
            onValueChange = { if (it.all { c -> c.isDigit() }) cantidad = it },
            label = { Text(stringResource(R.string.Quantity)) },
            placeholder = { Text("Introduce la cantidad de la cuenta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Comensales
        OutlinedTextField(
            value = comensales,
            onValueChange = { if (it.all { c -> c.isDigit() }) comensales = it },
            label = { Text("Comensales") },
            placeholder = { Text("Introduce el número de comensales") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Switch + Slider
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Redondear Propina")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = redondear,
                    onCheckedChange = { redondear = it }
                )
            }

            Slider(
                value = valoracion,
                onValueChange = { valoracion = it },
                valueRange = 0f..5f,
                steps = 4,
                enabled = redondear
            )

            if (redondear) {
                Text("Propina: ${(porcentaje * 100)}%")
            }
        }

        Button(
            onClick = { mostrarResultado = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }
        if(mostrarResultado){
            Text("Total con propina: %.2f €".format(totalConPropina))
            Text("Por persona: %.2f €".format(porPersona))
        }
    }
}





