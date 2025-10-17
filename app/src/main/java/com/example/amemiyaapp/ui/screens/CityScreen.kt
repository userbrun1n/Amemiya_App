package com.example.amemiyaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// NOTA: O import de 'BorderStroke' e o uso da propriedade 'border' no Card foram removidos.

@Composable
fun CityScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onDeviceLocationClick: () -> Unit
) {
    var selectedCity by remember { mutableStateOf<String?>(null) }
    val cities = listOf("São Paulo", "Rio de Janeiro", "Belo Horizonte", "Espírito Santo", "Curitiba", "Porto Alegre")
    var searchCity by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
        ) {

            // 1. Top Bar com Ícones
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botão Voltar (Seta)
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }

                // Ícones de Notificação, Ajuda e Perfil (alinhados à direita)
                Row {
                    // Notificações (Simulando o badge '20')
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color(0xFF66C8FF),
                                contentColor = Color.Black
                            ) { Text("20", fontSize = 8.sp) }
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notificações", tint = Color.Black, modifier = Modifier.size(24.dp))
                    }

                    Icon(Icons.Filled.QuestionMark, contentDescription = "Ajuda", tint = Color.Black, modifier = Modifier.size(24.dp).padding(horizontal = 4.dp))

                    // Ícone de Perfil (Simulação do Avatar)
                    Icon(Icons.Filled.Person, contentDescription = "Perfil", tint = Color.Black, modifier = Modifier.size(32.dp).padding(start = 8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Título Principal
            Text(
                text = "Por favor, nos informe sua localização",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Campo de Busca
            OutlinedTextField(
                value = searchCity,
                onValueChange = { searchCity = it },
                placeholder = { Text("Buscar Cidade", color = Color.Gray, fontSize = 16.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Gray) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Localização do Dispositivo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.GpsFixed, contentDescription = "GPS", tint = Color.Black, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Localização do Dispositivo",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Botão "Clique aqui"
                Button(
                    onClick = onDeviceLocationClick,
                    modifier = Modifier.height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Text("Clique aqui", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. Botão "Continuar"
            Button(
                onClick = { if (selectedCity != null) onContinue() },
                enabled = selectedCity != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.TouchApp,
                    contentDescription = "Continuar",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 6. Lista de Cidades (Cards)
            val filteredCities = cities.filter { it.contains(searchCity, ignoreCase = true) }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(filteredCities) { city ->
                    val isSelected = selectedCity == city

                    Card(
                        onClick = { selectedCity = city },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        // Removida a propriedade 'border' para resolver o erro 'BorderStroke'
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color.Black else Color.White,
                            contentColor = if (isSelected) Color.White else Color.Black
                        ),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = city,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 18.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}