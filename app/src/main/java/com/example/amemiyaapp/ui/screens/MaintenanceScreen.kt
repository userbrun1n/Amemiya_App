package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.amemiyaapp.R // Import necessário para R.drawable

// --- Estrutura de Dados (Modelo para os Serviços) ---
data class MaintenanceService(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageResId: Int // Recurso de imagem na pasta drawable
)

// --- Dados de Manutenção (Simulados) ---
val availableServices = listOf(
    MaintenanceService(
        id = 1,
        name = "Troca de Bateria",
        description = "Troca total da bateria do veículo",
        price = "650 R$",
        imageResId = R.drawable.bateria
    ),
    MaintenanceService(
        id = 2,
        name = "Troca de Óleo",
        description = "Substituição do óleo e filtro",
        price = "280 R$",
        imageResId = R.drawable.oleo
    ),
    MaintenanceService(
        id = 3,
        name = "Revisão Completa",
        description = "Verificação de 50 itens essenciais",
        price = "1.200 R$",
        imageResId = R.drawable.revisao
    ),
    MaintenanceService(
        id = 4,
        name = "Alinhamento e Balanceamento",
        description = "Ajuste completo de rodagem e pneus",
        price = "150 R$",
        imageResId = R.drawable.alinhamento
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceScreen(
    onBack: () -> Unit,
    onConfirm: (service: MaintenanceService) -> Unit // Agora passa o serviço selecionado
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manutenções", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF7F7F7) // Fundo muito claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- 1. Campo de Busca ---
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "", // Estado de busca não implementado
                onValueChange = { /* Lógica de busca */ },
                placeholder = { Text("Buscar", color = Color.Gray) },
                trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. Título da Seção ---
            Text(
                "Manutenções Disponíveis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- 3. Grid de Serviços ---
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(availableServices) { service ->
                    MaintenanceCard(service = service, onSelect = onConfirm)
                }
            }
        }
    }
}

// --- Componente de Card Individual ---
@Composable
fun MaintenanceCard(service: MaintenanceService, onSelect: (service: MaintenanceService) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                // Imagem do Serviço (simulando a foto do motor/peça)
                Image(
                    painter = painterResource(id = service.imageResId),
                    contentDescription = service.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Ícone de Favorito (coração)
                IconButton(
                    onClick = { /* Lógica de favoritar */ },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favoritar",
                        tint = Color.White,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(percent = 50))
                            .padding(4.dp)
                    )
                }
            }

            // Detalhes do Serviço
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = service.price,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }

            // Botão Selecionar
            Button(
                onClick = { onSelect(service) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Selecionar", color = Color.White)
            }
        }
    }
}