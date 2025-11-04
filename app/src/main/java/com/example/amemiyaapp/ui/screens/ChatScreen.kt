package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Modelo de Dados para as Mensagens
data class Message(val text: String, val isUser: Boolean, val timestamp: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(onBack: () -> Unit) {
    // Lista de mensagens iniciais (Estado que será atualizado)
    var messages by remember {
        mutableStateOf(listOf(
            Message("Olá! Como posso te ajudar hoje?", isUser = false, timestamp = "10:00"),
            Message("Estou com um erro ao registrar um comprovante de abastecimento.", isUser = true, timestamp = "10:01"),
            Message("Certo. Qual é o CNPJ do posto?", isUser = false, timestamp = "10:02")
        ))
    }
    var currentMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat com Consultor", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF0F0F0) // Fundo cinza claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // --- Área de Mensagens ---
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                reverseLayout = false, // Mantém a lista rolável para baixo
                verticalArrangement = Arrangement.Bottom // Mensagens recentes no final
            ) {
                items(messages) { message ->
                    MessageBubble(message = message)
                }
            }

            // --- Campo de Entrada de Texto ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = currentMessage,
                    onValueChange = { currentMessage = it },
                    placeholder = { Text("Mensagem...", color = Color.Gray) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    // Cores ajustadas para preto/branco/cinza
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))

                // Botão de Envio
                IconButton(
                    onClick = {
                        if (currentMessage.isNotBlank()) {
                            // 1. Adiciona a nova mensagem do usuário
                            val newMessage = Message(currentMessage.trim(), isUser = true, timestamp = "Agora")
                            messages = messages + newMessage
                            currentMessage = ""

                            // 2. Simula uma resposta do sistema após um pequeno delay
                            // TODO: (Futuramente, esta lógica seria feita por um backend)
                        }
                    },
                    enabled = currentMessage.isNotBlank(),
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (currentMessage.isNotBlank()) Color.Black else Color.LightGray)
                ) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "Enviar",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

// Composable para exibir uma bolha de mensagem
@Composable
fun MessageBubble(message: Message) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val color = if (message.isUser) Color.Black else Color.White
    val textColor = if (message.isUser) Color.White else Color.Black
    val shape = if (message.isUser) {
        RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp)
    } else {
        RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = color),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Text(
                text = message.text,
                color = textColor,
                modifier = Modifier.padding(10.dp)
            )
        }
        Text(
            text = message.timestamp,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        )
    }
}