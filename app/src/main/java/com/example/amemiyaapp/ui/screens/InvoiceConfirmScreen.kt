package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Usaremos a estrutura de dados InvoiceItem que definimos na InvoiceScreen
// Se houver um erro de 'Unresolved reference InvoiceItem', copie o data class
// da InvoiceScreen.kt para cá, ou defina-o em um arquivo de modelo compartilhado.
// Para este exemplo, assumimos que InvoiceItem está acessível.

// Dados simulados da fatura que está sendo paga (exemplo)
// Adicionando InvoiceItem e InvoiceStatus para garantir que o código funcione, caso não estejam importados:



val invoiceToConfirm = InvoiceItem(
    id = 101,
    description = "Revisão 20.000 KM",
    amount = "R$ 1.500,00",
    dueDate = "15/12/2025",
    status = InvoiceStatus.PENDING
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceConfirmScreen(
    onBack: () -> Unit,
    onPaymentConfirmed: () -> Unit // Ação que simula o sucesso e navega de volta ou para confirmação final
) {
    // Estado para simular o pagamento (mudará para TRUE ao clicar no botão)
    var isProcessing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmação de Pagamento", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF7F7F7)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Detalhes da Fatura ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp).fillMaxWidth()) {
                    Text(
                        "Fatura Selecionada",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        invoiceToConfirm.amount,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Linhas de detalhe
                    PaymentDetailRow(title = "Descrição", detail = invoiceToConfirm.description)
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentDetailRow(title = "Vencimento", detail = invoiceToConfirm.dueDate)
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentDetailRow(title = "Método", detail = "Cartão de Crédito (Final 1234)")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Simulação de Pagamento ---
            if (isProcessing) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // CORRIGIDO: Removido 'color = Color.Black' para resolver o erro de compilação
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Processando pagamento...", color = Color.Black)
                }
            } else {
                Text(
                    "Confirme o pagamento de ${invoiceToConfirm.amount} para finalizar a transação.",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Botão de Confirmação
                Button(
                    onClick = {
                        isProcessing = true
                        // Simula um atraso antes de confirmar
                        // Em um app real, aqui você faria a chamada API
                        // E chamaria onPaymentConfirmed() no sucesso
                        // Aqui, chamaremos a ação de navegação diretamente.
                        onPaymentConfirmed()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Confirmar Pagamento", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botão de Voltar
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy()
                ) {
                    Text("Cancelar", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun PaymentDetailRow(title: String, detail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Text(detail, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}