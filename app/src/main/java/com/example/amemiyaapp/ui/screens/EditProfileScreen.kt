package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.amemiyaapp.R // Certifique-se de que este import está correto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit, // Ação chamada ao salvar com sucesso (volta para o Profile)
    onEditPhotoClick: () -> Unit // NOVA AÇÃO: Ao clicar para editar a foto
) {
    // Dados simulados do perfil (devem ser passados ou carregados em um app real)
    var name by remember { mutableStateOf("Bruno Augusto") }
    var phone by remember { mutableStateOf("+55 89107 - 95286") }
    var email by remember { mutableStateOf("brunoaug@amemiya.com.br") }

    // Simulação da foto de perfil
    val profilePhotoRes = R.drawable.motorista2 // Substitua por um recurso de imagem real

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Perfil", color = Color.Black) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza o Avatar
        ) {
            // --- Componente de Edição de Foto ---
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 24.dp)
            ) {
                // 1. Imagem de Perfil
                Image(
                    painter = painterResource(id = profilePhotoRes),
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { onEditPhotoClick() }, // Torna a foto clicável
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                // 2. Ícone de Edição flutuante
                FloatingActionButton(
                    onClick = onEditPhotoClick,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd) // Posiciona no canto inferior direito
                        .offset(x = 4.dp, y = 4.dp), // Ajusta a posição levemente
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    Icon(
                        Icons.Filled.CameraAlt,
                        contentDescription = "Mudar Foto",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            // --- Fim da Edição de Foto ---

            Text(
                "Atualize suas informações pessoais.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Campo Nome
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome Completo") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Telefone
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Telefone") },
                leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = "Telefone") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botão Salvar
            Button(
                onClick = {
                    println("Salvando alterações: Nome=$name, Email=$email")
                    onSaveSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Salvar Alterações", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}