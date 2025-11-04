package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember

data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val time: String,
    val icon: ImageVector,
    val isUnread: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onBack: () -> Unit,
    onNotificationClick: (id: Int) -> Unit // Para o caso de uma notificação ser clicada
) {
    // Dados de exemplo para as notificações (simulando as 20 mencionadas na Home)
    val notifications = remember {
        listOf(
            NotificationItem(1, "Manutenção Agendada", "Sua troca de óleo está agendada para 05/11/2025.", "1h atrás", Icons.Filled.Info, true),
            NotificationItem(2, "Pagamento Confirmado", "Sua fatura de R$ 650 foi paga com sucesso.", "3h atrás", Icons.Filled.Info, true),
            NotificationItem(3, "Novo Artigo", "Confira o novo guia sobre pneus.", "1 dia atrás", Icons.Filled.Info, false),
            NotificationItem(4, "Lembrete", "Não se esqueça de registrar seu último abastecimento.", "2 dias atrás", Icons.Filled.Info, false)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificações", color = Color.Black) },
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
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notifications) { item ->
                NotificationCard(item = item, onClick = { onNotificationClick(item.id) })
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de Não Lida (a bolinha azul, se for o caso)
            if (item.isUnread) {
                Spacer(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF66C8FF)) // Cor do badge da Home
                )
                Spacer(modifier = Modifier.width(8.dp))
            } else {
                Spacer(modifier = Modifier.width(16.dp)) // Espaço para alinhar
            }

            // Ícone da Notificação (simples)
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.message,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = item.time,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}