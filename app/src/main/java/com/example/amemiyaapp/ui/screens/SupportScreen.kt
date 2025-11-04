package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults // ADICIONE ESTE NOVO IMPORT!

// Anotação necessária devido ao uso do TopAppBar no Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(onBack: () -> Unit, onChatClick: () -> Unit, onCallClick: () -> Unit, onFaqClick: () -> Unit) {

    // Cor de Fundo do Scaffold: Cinza Claro (padrão do seu app)
    val backgroundColor = Color(0xFFF0F0F0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajuda e Suporte", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White), // Barra Superior Branca
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = backgroundColor // Fundo Cinza Claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Como podemos ajudar?",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            SupportOptionCard(
                icon = Icons.Filled.ChatBubble,
                title = "Chat com Consultor",
                subtitle = "Inicie uma conversa em tempo real.",
                onClick = onChatClick
            )
            Spacer(modifier = Modifier.height(12.dp))

            SupportOptionCard(
                icon = Icons.Filled.HelpOutline,
                title = "Dúvidas Frequentes (FAQ)",
                subtitle = "Encontre respostas para as perguntas mais comuns.",
                onClick = onFaqClick
            )
            Spacer(modifier = Modifier.height(12.dp))

            SupportOptionCard(
                icon = Icons.Filled.ContactPhone,
                title = "Ligar para o Suporte",
                subtitle = "Atendimento 24 horas por dia.",
                onClick = onCallClick
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Ou envie uma mensagem:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            MessageForm()
        }
    }
}

// Composable para o Formulário de Mensagem
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageForm() {
    var message by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Detalhe seu problema", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors( // Use TextFieldDefaults.colors()
                // Cores do campo OutlinedTextField:
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            singleLine = false
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botão Preto e Branco (Estilo de Ação Principal)
        Button(
            onClick = { /* Lógica de envio */ },
            enabled = message.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray
            )
        ) {
            Text("Enviar Mensagem")
        }
    }
}

// Composable para cada opção de suporte (Ícone Preto)
@Composable
fun SupportOptionCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = Color.Black // Ícone Preto
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray.copy(alpha = 0.8f)) // Subtítulo Cinza
            }
        }
    }
}