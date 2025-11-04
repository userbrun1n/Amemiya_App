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

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onBack: () -> Unit,

) {
    var email by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // O Box simula a imagem de fundo escura
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Simulação da imagem de fundo: usando um cinza escuro para a parte da estrada
            .background(Color(0xFF333333))
    ) {
        // Coluna para o conteúdo principal, AGORA COBRINDO A TELA INTEIRA.
        Column(
            modifier = Modifier
                .fillMaxSize() // Usa fillMaxSize para cobrir a tela inteira
                .background(
                    Color.Black.copy(alpha = 0.85f), // Fundo preto opaco (simulando a 'caixa')
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                )
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
        ) {
            // 1. Linha do Título "Criar Conta" e Botão de Voltar (seta)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Título "Criar Conta" (parte superior na imagem)
                Text(
                    text = "Criar Conta",
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

            // 2. Cabeçalho principal "Olá! Vamos criar uma conta"
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Olá!",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Vamos criar uma conta",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 3. Campos de Texto

            // E-mail
            Text("E-mail", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            TextField( // <--- Lógica DarkTextField embutida
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Informe seu E-mail...", color = Color.Gray, fontSize = 16.sp) },
                label = null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Número (Telefone)
            Text("Número", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            TextField( // <--- Lógica DarkTextField embutida
                value = number,
                onValueChange = { number = it },
                placeholder = { Text("Informe seu Número...", color = Color.Gray, fontSize = 16.sp) },
                label = null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Senha
            Text("Senha", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            TextField( // <--- Lógica DarkTextField embutida
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Crie uma senha...", color = Color.Gray, fontSize = 16.sp) },
                label = null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Botão "Criar uma conta"
            Button(
                onClick = { /* depois vamos salvar e navegar */ },
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
                    text = "Criar uma conta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.TouchApp,
                    contentDescription = "Criar Conta",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Link "Já tem uma conta? Clique aqui"
            TextButton(onClick = onLoginClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                            append("Já tem uma conta? Clique ")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF66C8FF), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)) {
                            append("aqui")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}