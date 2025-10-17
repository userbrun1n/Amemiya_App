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

// NOTA: REMOVEMOS A FUNÇÃO DARKTEXTFIELD AQUI PARA EVITAR ERROS DE COMPILAÇÃO.

// Componente Auxiliar para o Campo de Entrada de Código (quadrado)
// A estilização é embutida aqui para garantir que funcione.
@Composable
fun CodeInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = {
            // Limita a entrada a 1 caractere (como em um campo de código)
            if (it.length <= 1) onValueChange(it)
        },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        ),
        singleLine = true,
        // Estilização DarkTextField embutida (cores, shape)
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF1F1F1F),
            unfocusedContainerColor = Color(0xFF1F1F1F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedTextColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(64.dp) // Altura maior para o quadrado
    )
}

@Composable
fun VerifyScreen(
    onVerified: () -> Unit,
    onBack: () -> Unit,
    onResendCodeClick: () -> Unit
) {
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)) // Fundo escuro (estrada)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                Text(
                    text = "Verificação Código 2",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 14.sp
                )

                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Cabeçalho principal
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Olá!",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Código de Validação",
                    color = Color.White,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Enviamos um código para o\n" + "número fornecido",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 3. Campos de Código (usando CodeInput)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CodeInput(value = code1, onValueChange = { code1 = it }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                CodeInput(value = code2, onValueChange = { code2 = it }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                CodeInput(value = code3, onValueChange = { code3 = it }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                CodeInput(value = code4, onValueChange = { code4 = it }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Botão "Verificar"
            Button(
                onClick = onVerified,
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
                    text = "Verificar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.TouchApp,
                    contentDescription = "Verificar",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }

            // O timer 00:34 (apenas texto)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "00:34",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Link "Não recebeu? Clique aqui!"
            TextButton(onClick = onResendCodeClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                            append("Não recebeu? Clique ")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF66C8FF), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)) {
                            append("aqui!")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}