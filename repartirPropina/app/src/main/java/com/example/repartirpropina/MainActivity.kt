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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
                        CampoCantidad();
                        Comensales();
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
                CampoCantidad()
                Comensales()
                Propina()
            }
        }
    }
}

@Composable
fun CampoCantidad() {
    var cantidad by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cantidad,
        onValueChange = { nuevoValor  ->
            if (nuevoValor.all { it.isDigit() }) {
                cantidad = nuevoValor
            }
        },
        label = { Text("Cantidad") }, // descripción del campo
        placeholder = { Text("Introduzca la cantidad de la cuenta") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Comensales() {
    var cantidad by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cantidad,
        onValueChange = { nuevoValor  ->
            if (nuevoValor.all { it.isDigit() }) {
                cantidad = nuevoValor
            }
        },
        label = { Text("Comensales") }, // descripción del campo
        placeholder = { Text("Introduce el número de comensales") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Propina(){
    var propina by remember { mutableStateOf(false) }

    Row(modifier= Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(text ="Redondear Propina")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = propina,
            onCheckedChange = {
                sleccionado -> propina = sleccionado
            }
        )
    }
}

@Composable
fun Valoracion(){
    
}


