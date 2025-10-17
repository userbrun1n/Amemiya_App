package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Parâmetros de navegação adaptados para a lista de opções da imagem
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    onCarDetailsClick: () -> Unit,
    onReceiptsClick: () -> Unit,
    onShareProfileClick: () -> Unit,
    onPersonalizationClick: () -> Unit,
    onEditProfileClick: () -> Unit // Nova ação para o ícone de lápis
) {
    // Dados simulados do perfil (baseados na imagem)
    val name = "Breno"
    val role = "Consultor / SP"
    val phone = "+55 89107 - 95286"
    val email = "fernando@gmail.com"

    // Lista de itens do menu inferior
    val menuItems = listOf(
        ProfileMenuItem("Detalhes do Seu Carro", Icons.Filled.ShoppingCart, onCarDetailsClick),
        ProfileMenuItem("Comprovantes / Reembolsos", Icons.Filled.Receipt, onReceiptsClick),
        ProfileMenuItem("Compartilhar Perfil", Icons.Filled.Person, onShareProfileClick),
        ProfileMenuItem("Personalização", Icons.Filled.Settings, onPersonalizationClick),
        ProfileMenuItem("Logout", Icons.Filled.ExitToApp, onLogout, isLogout = true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // Fundo cinza claro
    ) {
        // 1. Cabeçalho (Personalização e Botão Voltar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Personalização",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Seção do Perfil (Card Branco)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                // Avatar, Nome e Botão Editar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar (Círculo de Cor com Icon/Imagem)
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF66C8FF).copy(alpha = 0.8f)) // Fundo azul claro para simular imagem
                            .clickable(onClick = onEditProfileClick),
                        contentAlignment = Alignment.Center
                    ) {
                        // Ícone para simular a imagem do usuário
                        Icon(Icons.Filled.Person, contentDescription = "Foto de Perfil", tint = Color.White, modifier = Modifier.size(40.dp))

                        // Ícone de lápis no canto inferior direito da imagem
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar Foto",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .align(Alignment.BottomEnd)
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Nome e Cargo/Localização
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = role,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    // Ícone de lápis para editar nome/dados
                    IconButton(onClick = onEditProfileClick) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar Perfil",
                            tint = Color.Black.copy(alpha = 0.7f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Linha do Telefone
                DetailRow(icon = Icons.Filled.Phone, text = phone)
                Spacer(modifier = Modifier.height(12.dp))

                // Linha do Email
                DetailRow(icon = Icons.Filled.ChatBubble, text = email)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Lista de Opções (Menu)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f), // Ocupa o restante do espaço
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                menuItems.forEach { item ->
                    MenuItemRow(item = item)
                    // Adiciona um divisor (linha fina) entre os itens, exceto o último
                    if (item != menuItems.last()) {
                        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

// --- Componentes Auxiliares ---

data class ProfileMenuItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val isLogout: Boolean = false
)

@Composable
fun DetailRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.7f),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.Black, fontSize = 16.sp)
    }
}

@Composable
fun MenuItemRow(item: ProfileMenuItem) {
    val contentColor = if (item.isLogout) Color.Red else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = item.onClick)
            .padding(vertical = 14.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = contentColor.copy(alpha = 0.8f),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = contentColor,
            fontSize = 16.sp,
            fontWeight = if (item.isLogout) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}