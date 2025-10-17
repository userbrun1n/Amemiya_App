package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MaintenanceScreen(
    onBack: () -> Unit,
    onConfirm: () -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf("") }

    val services = listOf("Troca de óleo", "Revisão completa", "Alinhamento e balanceamento", "Freios")

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
            text = "Agendar Manutenção",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            label = { Text("Data (dd/mm/aaaa)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Selecione o serviço:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        services.forEach { service ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedService == service,
                    onClick = { selectedService = service }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(service)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { if (selectedDate.isNotBlank() && selectedService.isNotBlank()) onConfirm() },
            enabled = selectedDate.isNotBlank() && selectedService.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar Agendamento")
        }
    }
}
