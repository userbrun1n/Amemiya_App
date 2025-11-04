package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// --- Estrutura de Dados para Faturas ---
data class InvoiceItem(
    val id: Int,
    val description: String,
    val amount: String,
    val dueDate: String,
    val status: InvoiceStatus
)

enum class InvoiceStatus { PENDING, PAID }

// --- Dados Simulados de Faturas ---
val simulatedInvoices = listOf(
    // Faturas Pendentes
    InvoiceItem(101, "Revisão 20.000 KM", "R$ 1.500,00", "15/12/2025", InvoiceStatus.PENDING),
    InvoiceItem(102, "Troca de Pneus e Alinhamento", "R$ 890,00", "01/01/2026", InvoiceStatus.PENDING),

    // Faturas Pagas
    InvoiceItem(201, "Troca de Óleo e Filtro", "R$ 350,00", "20/10/2025", InvoiceStatus.PAID),
    InvoiceItem(202, "Abastecimento - 05/11", "R$ 250,00", "06/11/2025", InvoiceStatus.PAID),
    InvoiceItem(203, "Freios e Fluido", "R$ 720,00", "15/09/2025", InvoiceStatus.PAID)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceScreen(
    onBack: () -> Unit,
    onInvoiceClick: (invoiceId: Int) -> Unit, // Ação ao clicar na fatura (detalhes)
    onPayClick: (invoiceId: Int) -> Unit // Ação ao clicar em Pagar
) {
    val pendingInvoices = simulatedInvoices.filter { it.status == InvoiceStatus.PENDING }
    val paidInvoices = simulatedInvoices.filter { it.status == InvoiceStatus.PAID }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minhas Faturas", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF7F7F7) // Fundo cinza claro
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // --- 1. Faturas Pendentes ---
            if (pendingInvoices.isNotEmpty()) {
                item {
                    InvoiceSectionTitle("Faturas Pendentes (${pendingInvoices.size})", Color(0xFFCC0000))
                }
                items(pendingInvoices) { invoice ->
                    InvoiceCard(
                        invoice = invoice,
                        onCardClick = onInvoiceClick,
                        onPayClick = onPayClick
                    )
                }
            } else {
                item {
                    InvoiceSectionTitle("Faturas Pendentes (0)", Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "🎉 Parabéns! Você não tem faturas pendentes no momento.",
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = Color.Gray
                    )
                }
            }

            // --- 2. Faturas Pagas ---
            item {
                InvoiceSectionTitle("Histórico de Faturas Pagas (${paidInvoices.size})", Color.Black)
            }
            items(paidInvoices) { invoice ->
                InvoiceCard(
                    invoice = invoice,
                    onCardClick = onInvoiceClick,
                    onPayClick = onPayClick // Botão de pagamento estará desabilitado para PAID
                )
            }
        }
    }
}

// --- Componentes Auxiliares ---

@Composable
fun InvoiceSectionTitle(title: String, color: Color) {
    Text(
        title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun InvoiceCard(
    invoice: InvoiceItem,
    onCardClick: (invoiceId: Int) -> Unit,
    onPayClick: (invoiceId: Int) -> Unit
) {
    val statusColor = when (invoice.status) {
        InvoiceStatus.PENDING -> Color(0xFFCC0000) // Vermelho
        InvoiceStatus.PAID -> Color(0xFF00AA00) // Verde
    }

    val statusText = when (invoice.status) {
        InvoiceStatus.PENDING -> "Pendente"
        InvoiceStatus.PAID -> "Paga"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onCardClick(invoice.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Descrição e Valor
                Text(
                    invoice.description,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    invoice.amount,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = statusColor
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Data de Vencimento
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Vencimento: ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        invoice.dueDate,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }

            // Ações (Botão ou Status)
            Column(horizontalAlignment = Alignment.End) {
                if (invoice.status == InvoiceStatus.PENDING) {
                    Button(
                        onClick = { onPayClick(invoice.id) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Pagar", color = Color.White)
                    }
                } else {
                    // Status de Fatura Paga
                    Text(
                        statusText,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = statusColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Mais opções",
                        tint = Color.Gray,
                        modifier = Modifier.clickable { /* Ação de ver detalhes */ }
                    )
                }
            }
        }
    }
}