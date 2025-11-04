package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// --- Estrutura de Dados (Modelo para Itens de Histórico) ---
data class HistoryItem(
    val title: String,
    val dateCompleted: String,
    val nextMaintenance: String,
    val isFavorite: Boolean = false // Simula o coração
)

// --- Dados Simulados Agrupados por Mês ---
val historyData = mapOf(
    "Esse mês" to listOf(
        HistoryItem("Troca - fluido de freio", "02/11/2025", "02/11/2026"),
        HistoryItem("Revisão de 10.000 KM", "01/11/2025", "01/05/2026", isFavorite = true),
    ),
    "Mês 10/2025" to listOf(
        HistoryItem("Troca de óleo", "15/10/2025", "15/01/2026"),
        HistoryItem("Alinhamento e Balanceamento", "05/10/2025", "05/04/2026"),
    ),
    "Mês 09/2025" to listOf(
        HistoryItem("Fatura de Setembro", "20/09/2025", "Pago"),
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(onBack: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Vazio para centralizar os elementos */ },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    // Botão de voltar (no canto superior esquerdo, seguindo o padrão)
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                actions = {
                    // Ícones de notificação e perfil (simulando a barra superior do design)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /* Navegar para Notificações */ }) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notificações", tint = Color.Black)
                        }
                        IconButton(onClick = { /* Navegar para Perfil */ }) {
                            // Usando FavoriteBorder como um ícone de placeholder para o perfil/círculo
                            Icon(Icons.Filled.FavoriteBorder, contentDescription = "Perfil", tint = Color.Black)
                        }
                    }
                }
            )
        },
        containerColor = Color.White // Fundo branco
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                // Título principal e subtítulo (Olá, Bruno)
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("Olá, Bruno", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Veja o histórico de manutenções aqui", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp) // Linha separadora
                }
            }

            // --- Itera sobre o mapa de histórico agrupado ---
            historyData.forEach { (month, items) ->
                item {
                    // Título do Mês (Ex: Esse mês, Mês 10/2025)
                    Text(
                        month,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                // Lista de Itens do Mês
                items(items) { item ->
                    HistoryCard(item = item)
                }
            }
        }
    }
}

// --- Componente de Card Individual do Histórico ---
@Composable
fun HistoryCard(item: HistoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { /* Detalhes do histórico */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)), // Fundo levemente cinza
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Manutenção realizada em",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    "${item.dateCompleted} - Próx: ${item.nextMaintenance}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            // Ícone de Coração (simulado)
            IconButton(onClick = { /* Lógica de favoritar */ }) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritar",
                    tint = if (item.isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}