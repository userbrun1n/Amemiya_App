package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import com.example.amemiyaapp.R // IMPORTANTE: Você precisará desta importação para usar os drawables (imagens)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsScreen(
    onBack: () -> Unit,
    onMaintenanceClick: () -> Unit,
    onInvoiceClick: () -> Unit
) {
    // Dados simulados
    val carMake = "Honda"
    val carModel = "Civic"
    val carPlate = "BY7T54W3"
    val carColor = "Vermelho" // Cor de texto simulada

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Seu Carro", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Lógica para abrir o menu */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color.White // Fundo branco
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- 1. Bloco de Seleção e Busca (Topo) ---
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Logo/Marca (Simulação de um Card de Marca)
                Box(
                    modifier = Modifier
                        .size(90.dp, 60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    // Substitua 'R.drawable.hyundai_logo' pelo seu recurso real
                    // Para o exemplo, usaremos o 'painterResource(id = R.drawable.ic_launcher_foreground)'
                    Icon(
                        painter = painterResource(id = R.drawable.honda), // Usando um ícone existente como placeholder
                        contentDescription = "Logo da Marca",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Campo de Busca
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Não é funcional, apenas placeholder */ },
                    placeholder = { Text("Outros modelos para usar", color = Color.Gray) },
                    trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedContainerColor = Color(0xFFF0F0F0),
                        unfocusedContainerColor = Color(0xFFF0F0F0),
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. Bloco da Imagem do Carro e Dados Principais ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEFF2)), // Fundo levemente cinza/azul
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    // Imagem do Carro
                    Image(
                        painter = painterResource(id = R.drawable.civic),
                        contentDescription = "Imagem do Carro",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.weight(0.6f)
                    )

                    // Dados flutuantes
                    Column(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$carMake $carModel",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = carPlate,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- 3. Placeholder do GPS/Mapa ---
            Text(
                "Localização em Tempo Real",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Simulação de Mapa (Placeholder)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
                    .clickable { /* Futura integração de mapa */ },
                contentAlignment = Alignment.Center
            ) {
                // Substitua pelo seu mapa real se houver integração futura
                Text("Mapa/GPS Aqui", color = Color.DarkGray, fontSize = 20.sp)

                // Exemplo de uma imagem de mapa estática para simular o layout
                Image(
                    painter = painterResource(id = R.drawable.mapa), // Usando um ícone existente como placeholder de mapa
                    contentDescription = "Mapa com Trajeto GPS",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(0.8f) // Imagem menor para parecer um mapa
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Os botões de ação foram removidos para seguir o layout da imagem,
            // mas você pode recolocá-los se forem necessários.

            // Botões de Ação (Comentados, mas mantidos para referência)
            /*
            Button(onClick = onMaintenanceClick, modifier = Modifier.fillMaxWidth()) {
                Text("Agendar Manutenção")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onInvoiceClick, modifier = Modifier.fillMaxWidth()) {
                Text("Gerar Fatura")
            }
            */
        }
    }
}