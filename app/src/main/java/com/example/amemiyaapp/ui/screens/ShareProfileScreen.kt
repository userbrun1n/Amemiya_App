package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Importe para ações nativas (simulação de compartilhamento)
import android.content.Intent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareProfileScreen(
    onBack: () -> Unit
) {
    // Dados simulados do usuário
    val userId = "4986753623"
    val userName = "Bruno Augusto"

    // O link de compartilhamento que queremos gerar, incluindo o ID
    // Ex: "https://amemiyaapp.com/perfil?id=4986753623"
    val profileLink = remember { "Seu ID de Perfil Amemiya: $userId" }

    // Contexto nativo do Android para acionar o compartilhamento
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compartilhar Perfil", color = Color.Black) },
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Cartão de Exibição do ID
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seu ID de Funcionário Amemiya",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = userId,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão Copiar Link
                    Button(
                        onClick = {
                            // Em um app real, aqui você copiaria o link para o clipboard
                            // Simulação:
                            val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                            val clip = android.content.ClipData.newPlainText("ID do Perfil", profileLink)
                            clipboard.setPrimaryClip(clip)
                            // Adicionar um Toast ou Snackbar de sucesso aqui
                            println("ID copiado: $profileLink")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66C8FF)), // Azul claro
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(Icons.Filled.ContentCopy, contentDescription = "Copiar", modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Copiar ID", color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão Compartilhar (aciona a tela nativa do Android)
            Button(
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_SUBJECT, "Meu Perfil Amemiya")
                        putExtra(Intent.EXTRA_TEXT, "Compartilhando meu ID ($userName): $profileLink")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Compartilhar perfil via"))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Share, contentDescription = "Compartilhar", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Compartilhar Perfil", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Empurra o conteúdo para cima

            Text(
                text = "Este ID é único e pode ser usado por colegas para identificação rápida no sistema.",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}