package com.example.amemiyaapp.ui.screens



import androidx.compose.foundation.layout.*

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp



@Composable

fun CarDetailsScreen(

    onBack: () -> Unit,

    onMaintenanceClick: () -> Unit,

    onInvoiceClick: () -> Unit

) {

    var carModel by remember { mutableStateOf("Nissan Silvia S15") }

    var carPlate by remember { mutableStateOf("ABC-1234") }

    var carYear by remember { mutableStateOf("2002") }



    Column(

        modifier = Modifier

            .fillMaxSize()

            .padding(24.dp)

    ) {

// Botão de voltar

        TextButton(onClick = onBack) {

            Text("← Voltar")

        }



        Spacer(modifier = Modifier.height(16.dp))



        Text(

            text = "Detalhes do Carro",

            style = MaterialTheme.typography.headlineMedium

        )



        Spacer(modifier = Modifier.height(24.dp))



        Text("Modelo: $carModel", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(8.dp))



        Text("Placa: $carPlate", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(8.dp))



        Text("Ano: $carYear", style = MaterialTheme.typography.bodyLarge)



        Spacer(modifier = Modifier.height(32.dp))



        Button(

            onClick = onMaintenanceClick,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Agendar Manutenção")

        }



        Spacer(modifier = Modifier.height(16.dp))



        Button(

            onClick = onInvoiceClick,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Gerar Fatura")

        }

    }

}