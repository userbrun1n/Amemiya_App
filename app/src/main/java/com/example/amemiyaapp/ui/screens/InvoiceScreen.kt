package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InvoiceScreen(
    onBack: () -> Unit,
    onSent: () -> Unit
) {
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Voltar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Gerar Fatura", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Valor (R$)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { if (amount.isNotBlank()) onSent() },
            enabled = amount.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Fatura")
        }
    }
}
