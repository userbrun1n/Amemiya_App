package com.example.amemiyaapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Modelo de Dados para as Perguntas e Respostas
data class FaqItem(val question: String, val answer: String)

// --- LISTA DE DÚVIDAS GENÉRICAS ---
val genericFaqs = listOf(
    FaqItem(
        question = "1. Como registrar um novo abastecimento?",
        answer = "Para registrar um novo abastecimento, vá para a tela 'Início', selecione o serviço 'Abastecimento' e siga as instruções para inserir o valor, a quilometragem atual e a nota fiscal."
    ),
    FaqItem(
        question = "2. Quando devo agendar a próxima manutenção?",
        answer = "Recomendamos que você siga o plano de manutenção preventiva sugerido pelo fabricante do seu veículo. O aplicativo irá notificá-lo com base na quilometragem registrada. Você pode verificar seu histórico na aba 'Registros'."
    ),
    FaqItem(
        question = "3. O que fazer se eu esquecer minha senha?",
        answer = "Na tela de 'Login', clique em 'Esqueci minha senha'. Você receberá um e-mail com um link para redefinição. Por motivos de segurança, não podemos redefinir a senha por chat ou telefone."
    ),
    FaqItem(
        question = "4. Posso usar o app com mais de um veículo?",
        answer = "Sim. Você pode adicionar e gerenciar múltiplos veículos através da seção 'Detalhes do Seu Carro' na tela de 'Perfil'. Todos os registros e manutenções serão separados por veículo."
    ),
    FaqItem(
        question = "5. Como entrar em contato com o suporte em caso de emergência?",
        answer = "Para emergências, por favor, utilize a opção 'Ligar para o Suporte' disponível no menu anterior. Nosso atendimento telefônico é 24 horas."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dúvidas Frequentes (FAQ)", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF0F0F0) // Fundo Cinza Claro
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(genericFaqs) { item ->
                FaqItemCard(item = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// Composable para cada item de Pergunta e Resposta (Expansível)
@Composable
fun FaqItemCard(item: FaqItem) {
    // Estado para controlar se a resposta está visível ou não
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }, // Alterna o estado ao clicar
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 300)) // Anima o tamanho
        ) {
            // --- Linha da Pergunta ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.question,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Recolher" else "Expandir",
                    tint = Color.Black
                )
            }

            // --- Bloco da Resposta (Visível apenas se expandido) ---
            if (isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.answer,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}