package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Componente Customizado para o campo de texto (TextField escuro)
// Corrigido para garantir que todos os imports e a sintaxe estejam corretos.
@Composable
fun DarkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                label,
                color = Color.Gray, // Cor do texto de placeholder
                fontSize = 16.sp
            )
        },
        // Remove rótulo externo, usando apenas o placeholder
        label = null,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF1F1F1F), // Fundo quase preto (TextField)
            unfocusedContainerColor = Color(0xFF1F1F1F),
            focusedIndicatorColor = Color.Transparent, // Remove a linha indicadora
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedTextColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp), // Cantos arredondados
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun LoginScreen( // Esta é a tela de Login/Verificação (image_d31b84.png)
    onSignUpClick: () -> Unit, // Navega para a tela de Cadastro
    onBack: () -> Unit,
    onGetCode: () -> Unit // Ação do botão "Obter código"
) {
    // Apenas o número de telefone é necessário para esta tela
    var number by remember { mutableStateOf("") }

    // O Box simula a imagem de fundo escura
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Simulação da imagem de fundo: usando um cinza escuro para a parte da estrada
            .background(Color(0xFF333333))
    ) {
        // Coluna para o conteúdo principal, COBRINDO A TELA INTEIRA.
        Column(
            modifier = Modifier
                .fillMaxSize() // Garante que o fundo preto cubra a tela toda
                .background(
                    Color.Black.copy(alpha = 0.85f), // Fundo preto opaco
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                )
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
        ) {
            // 1. Linha do Título "Verificação Código" e Botão de Voltar (seta)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Título "Verificação Código"
                Text(
                    text = "Verificação Código",
                    color = Color.White.copy(alpha = 0.5f), // Cor cinza claro
                    fontSize = 14.sp
                )

                // Botão de voltar (seta)
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White // Ícone de seta branco
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Cabeçalho principal "Olá! Bem vindo de volta!"
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Olá!",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bem vindo de volta!",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 3. Campo de Texto (Telefone)

            // Título do campo
            Text("Digite seu telefone", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp)) // Espaçamento maior antes do campo

            // Campo de telefone usando o componente customizado
            DarkTextField(
                value = number,
                onValueChange = { number = it },
                label = "Ex: (11) 97655-7765",
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Botão "Obter código"
            Button(
                onClick = onGetCode,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Obter código",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.TouchApp,
                    contentDescription = "Obter código",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Link "Criar uma nova conta?"
            TextButton(onClick = onSignUpClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                            append("Criar uma nova ")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF66C8FF), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)) {
                            append("conta?")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}