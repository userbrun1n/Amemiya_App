package com.example.amemiyaapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import java.io.File


// Funções utilitárias para a câmera (deve estar no mesmo arquivo ou ter o import correto)
fun getTemporaryImageUri(context: android.content.Context): Uri {
    val tempFile = File.createTempFile("temp_image", ".jpg", context.cacheDir)
    return androidx.core.content.FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider", // Necessário configurar no AndroidManifest.xml
        tempFile
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelScreen(onBack: () -> Unit, onConfirm: (fuelAmount: String, odometer: String, receiptUri: Uri?) -> Unit) {
    val context = LocalContext.current
    var fuelAmount by remember { mutableStateOf("") }
    var odometer by remember { mutableStateOf("") }
    var receiptImageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher para abrir a câmera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            // Sucesso: URI já está em receiptImageUri
        } else {
            receiptImageUri = null // Falha ou cancelamento
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Abastecimento", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = Color(0xFFF0F0F0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Insira os dados e tire a foto do recibo",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Campo para Valor do Abastecimento
            OutlinedTextField(
                value = fuelAmount,
                onValueChange = { fuelAmount = it },
                label = { Text("Valor Abastecido (R$)", color = Color.Gray) },
                // REMOVIDO: keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black, unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Black, focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Gray, focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo para Quilometragem Atual
            OutlinedTextField(
                value = odometer,
                onValueChange = { odometer = it },
                label = { Text("Quilometragem Atual (KM)", color = Color.Gray) },
                // REMOVIDO: keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black, unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Black, focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Gray, focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Área para a Foto do Recibo ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        // Antes de abrir a câmera, criamos um URI temporário para a imagem
                        val uri = getTemporaryImageUri(context)
                        receiptImageUri = uri
                        cameraLauncher.launch(uri) // Abre a câmera para tirar a foto
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                if (receiptImageUri != null) {
                    // Exibe a imagem tirada
                    Image(
                        painter = rememberImagePainter(receiptImageUri),
                        contentDescription = "Foto do Recibo",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Placeholder para tirar foto
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Filled.CameraAlt,
                            contentDescription = "Tirar Foto",
                            modifier = Modifier.size(64.dp),
                            tint = Color.Gray
                        )
                        Text(
                            "Tirar Foto do Recibo",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Botão de Confirmação
            Button(
                onClick = { onConfirm(fuelAmount, odometer, receiptImageUri) },
                enabled = fuelAmount.isNotBlank() && odometer.isNotBlank() && receiptImageUri != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar Abastecimento", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}