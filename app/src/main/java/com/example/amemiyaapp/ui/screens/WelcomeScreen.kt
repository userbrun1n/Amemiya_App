package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.Image // Importar para usar o componente Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource // Importar para carregar a imagem do drawable
import com.example.amemiyaapp.R // Necessário para acessar seus recursos em res/drawable


@Composable
fun WelcomeScreen(onNext: () -> Unit) {
    // Cor de fundo levemente cinza/off-white para a tela toda
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F7F7) // Cinza bem claro
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Conteúdo Superior (Texto e Logo) - Centralizado verticalmente
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter) // Alinhamento para a parte de cima, com espaçamento
                    .padding(top = 100.dp), // Espaçamento do topo
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto: "Bem Vindo à melhor maneira de cuidar do veículo"
                Text(
                    text = buildAnnotatedString {
                        append("Bem Vindo à ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("melhor")
                        }
                        append("\nmaneira de cuidar do veículo")
                    },
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray, // Cor cinza escuro
                    lineHeight = 28.sp // Aumenta o espaçamento entre as linhas
                )

                Spacer(modifier = Modifier.height(100.dp)) // Espaçamento entre o texto e o logo

                // Logo AMEMIYA (Substituição do Box pela Imagem)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // CÓDIGO ALTERADO PARA USAR A IMAGEM R.drawable.logo
                    Image(
                        painter = painterResource(id = R.drawable.logo), // <-- AGORA CARREGA SEU ARQUIVO LOGO
                        contentDescription = "Logo Amemiya",
                        modifier = Modifier
                            .size(24.dp), // Mantém o tamanho original do placeholder
                        // contentScale.Fit (ou ContentScale.Crop) pode ser usado se precisar de ajuste.
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AMEMIYA",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Cor preta para o texto do logo
                    )
                }

                // Há um texto "Discover the Best Deals on Pre-Owned Cars" na imagem,
                // mas está quase invisível. Foi omitido para clareza.
            }

            // Botão "Vamos lá!?" - Alinhado à parte inferior
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(56.dp), // Altura maior para o botão
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Fundo preto
                    contentColor = Color.White // Texto branco
                ),
                shape = RoundedCornerShape(8.dp) // Levemente arredondado
            ) {
                // Texto e ícone do botão
                Text(
                    text = "Vamos lá!?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}