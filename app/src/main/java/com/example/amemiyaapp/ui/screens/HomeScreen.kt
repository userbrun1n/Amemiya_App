package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build // NOVO
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Headset // NOVO
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalGasStation // NOVO
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector // NOVO
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image // Necessário para usar o componente Image
import androidx.compose.ui.res.painterResource // ESSENCIAL: Resolve a referência à função painterResource
import com.example.amemiyaapp.R // Necessário para acessar R.drawable.xxx
import androidx.compose.ui.graphics.painter.Painter
import com.example.amemiyaapp.navigation.Routes

@Composable
fun HomeScreen(
    onFuelClick: () -> Unit,
    onMaintenanceClick: () -> Unit,
    onSupportClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBack: () -> Unit,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onArticleClick: (route: String) -> Unit,
    // NOVOS PARÂMETROS DA BOTTOM BAR:
    onRecordsClick: () -> Unit, // Para "Registros"
    onProfileNavClick: () -> Unit, // Para "Perfil" na nav bar
    onSupportNavClick: () -> Unit // Para "Ajuda" na nav bar
) {
    var searchText by remember { mutableStateOf("") }

    // VARIÁVEL DA FOTO DE PERFIL ADICIONADA AQUI
    val profilePhotoRes = R.drawable.motorista2 // REQUER: um arquivo profile_placeholder no res/drawable

    val bottomItems = listOf("Início", "Resgistros", "Perfil", "Ajuda")
    var selectedBottomItem by remember { mutableStateOf("Início") }

    Scaffold(
        bottomBar = {
            // 5. Bottom Navigation Bar
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                bottomItems.forEach { item ->
                    val isSelected = selectedBottomItem == item
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedBottomItem = item
                            // EXECUTA A NAVEGAÇÃO
                            when (item) {
                                "Início" -> { /* Não faz nada, já está em Home */ }
                                "Resgistros" -> onRecordsClick() // Chama a função passada pelo NavHost
                                "Perfil" -> onProfileNavClick()
                                "Ajuda" -> onSupportNavClick()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = when (item) {
                                    "Início" -> Icons.Filled.Home
                                    "Resgistros" -> Icons.Filled.ShoppingCart
                                    "Perfil" -> Icons.Filled.Edit
                                    "Ajuda" -> Icons.Filled.ChatBubble
                                    else -> Icons.Filled.Home
                                },
                                contentDescription = item,
                                tint = if (isSelected) Color.Black else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                item,
                                color = if (isSelected) Color.Black else Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        // Coluna principal para o conteúdo da tela
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF0F0F0))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {

            // 1. Top Bar (Cabeçalho)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Título/Voltar
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Página Inicial",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }

                // Ícones de Notificação, Ajuda e Perfil
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Notificações (Badge '20')
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color(0xFF66C8FF),
                                contentColor = Color.Black
                            ) { Text("2", fontSize = 8.sp) }
                        },
                        modifier = Modifier.clickable(onClick = onNotificationClick)
                    ) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notificações", tint = Color.Black, modifier = Modifier.size(24.dp))
                    }

                    Icon(Icons.Filled.QuestionMark, contentDescription = "Ajuda", tint = Color.Black, modifier = Modifier.size(24.dp).padding(horizontal = 12.dp))

                    // Ícone de Perfil (Avatar) - SUBSTITUÍDO PELA IMAGEM DE PERFIL
                    Image(
                        painter = painterResource(id = profilePhotoRes),
                        contentDescription = "Perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(androidx.compose.foundation.shape.CircleShape) // Usando CircleShape
                            .clickable(onClick = onProfileClick)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Campo de Busca
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Buscar", color = Color.Gray, fontSize = 16.sp) },
                trailingIcon = { IconButton(onClick = onSearchClick) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Black) }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Título "Selecione o Serviço"
            Text(
                text = "Selecione o Serviço",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Seção "Selecione o Serviço" (Cards de Ícones)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Abastecimento
                ServiceCard(
                    icon = Icons.Filled.LocalGasStation,
                    label = "Abastecimento",
                    onClick = onFuelClick,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Manutenção
                ServiceCard(
                    icon = Icons.Filled.Build,
                    label = "Manutenção",
                    onClick = onMaintenanceClick,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Suporte
                ServiceCard(
                    icon = Icons.Filled.Headset,
                    label = "Suporte",
                    onClick = onSupportClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. Título "Saiba como realizar as atividades:"
            Text(
                text = "Saiba como realizar as atividades:",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 6. Lista de Cards Informativos
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    InfoCard(
                        title = "Como registrar a nota de abastecimento?",
                        subtitle = "Saiba mais sobre o processo",
                        imagePainter = painterResource(id = R.drawable.abastecimento),
                        onClick = { onArticleClick(Routes.ArticleAbastecimento)}
                    )
                }
                item {
                    InfoCard(
                        title = "Quando realizar as manutenções?",
                        subtitle = "Guia para esclarecer dúvidas",
                        imagePainter = painterResource(id = R.drawable.manutencao), // <-- AQUI!
                        onClick = { onArticleClick(Routes.ArticleManutencao)}
                    )
                }
                item {
                    InfoCard(
                        title = "O que fazer caso haja erros?",
                        subtitle = "Não se preocupe! Iremos te ajudar",
                        imagePainter = painterResource(id = R.drawable.erro), // <-- AQUI!
                        onClick = { onArticleClick(Routes.ArticleErros)}
                    )
                }
            }
        }
    }
}

// --- Componentes Auxiliares ---

@Composable
fun ServiceCard(
    icon: ImageVector, // Usando ImageVector
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(32.dp), tint = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, fontSize = 14.sp, textAlign = TextAlign.Center, color = Color.Black)
        }
    }
}

// NO SEU HomeScreen.kt
@Composable
fun InfoCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    imagePainter: androidx.compose.ui.graphics.painter.Painter? = null // ESTE É O NOVO PARÂMETRO
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bloco da imagem
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray), // Fundo cinza para o caso de não ter imagem
            contentAlignment = Alignment.Center
        ) {
            // Se um imagePainter for fornecido, use a imagem real
            if (imagePainter != null) {
                Image( // Use o componente Image para exibir o Painter
                    painter = imagePainter,
                    contentDescription = null, // Ou uma descrição mais específica
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Garante que a imagem preencha o espaço
                )
            } else {
                // Caso contrário, use o placeholder (ícone de pessoa)
                Icon(Icons.Filled.Person, contentDescription = "Imagem", tint = Color.Gray, modifier = Modifier.align(Alignment.Center))
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}