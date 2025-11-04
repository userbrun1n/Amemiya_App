package com.example.amemiyaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amemiyaapp.ui.screens.*
import androidx.compose.foundation.Image // Necessário para usar o componente Image
import androidx.compose.ui.layout.ContentScale // Necessário para ContentScale.Crop
import com.example.amemiyaapp.R // Necessário para acessar R.drawable.xxx
import com.example.amemiyaapp.ui.screens.ArticleScreen
import com.example.amemiyaapp.ui.screens.SupportScreen
import com.example.amemiyaapp.ui.screens.ChatScreen // Novo import
import com.example.amemiyaapp.ui.screens.FaqScreen


object Routes {
    const val Welcome = "welcome"
    const val SignUp = "signup"
    const val Login = "login"
    const val Verify = "verify"
    const val City = "city"
    const val Home = "home" // <--- HOME_ROUTE CORRIGIDO AQUI
    const val Profile = "profile"
    const val CarDetails = "car_details"
    const val Invoice = "invoice" // Rota Principal da Lista de Faturas
    const val InvoiceConfirm = "invoice_confirm" // Rota da Confirmação de Pagamento
    const val Maintenance = "maintenance"
    const val History = "history"
    const val Support = "support"
    const val ArticleAbastecimento = "article_abastecimento_screen"
    const val ArticleManutencao = "article_manutencao_screen"
    const val ArticleErros = "article_erros_screen"
    const val Chat = "chat_screen"       // Rota do Chat
    const val Faq = "faq_screen"
    const val Fuel = "fuel_screen"
    // REMOVIDO: INVOICE_ROUTE e INVOICE_CONFIRM_ROUTE duplicados para usar 'Invoice' e 'InvoiceConfirm'
    const val EditProfile = "edit_profile"
    const val Notifications = "notifications"
    const val ShareProfile = "share_profile"
}

@Composable
fun AppNavHost() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.Welcome) {
        composable(Routes.Welcome) { WelcomeScreen(onNext = { nav.navigate(Routes.SignUp) }) }
        composable(Routes.SignUp) {
            SignUpScreen(
                onLoginClick = { nav.navigate(Routes.Login) },
                onBack = { nav.popBackStack() })
        }
        composable(Routes.Login) {
            LoginScreen(
                onSignUpClick = { nav.navigate(Routes.SignUp) },
                onGetCode = { nav.navigate(Routes.Verify) },
                onBack = { nav.popBackStack() })
        }
        composable(Routes.ShareProfile) {
            ShareProfileScreen(
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.Verify) {
            VerifyScreen(
                onVerified = { nav.navigate(Routes.City) },
                onResendCodeClick = { println("Código reenviado!") },
                onBack = { nav.popBackStack() })
        }
        composable(Routes.EditProfile) {
            EditProfileScreen(
                onBack = { nav.popBackStack() },
                onSaveSuccess = { nav.popBackStack() }, // Volta para a tela de Perfil
                onEditPhotoClick = { println("Ação: Abrir seletor de fotos ou câmera!") }
            )
        }
        composable(Routes.Notifications) {
            NotificationsScreen(
                onBack = { nav.popBackStack() },
                onNotificationClick = { notificationId ->
                    println("Notificação #$notificationId clicada!")
                    // Aqui você pode navegar para a tela de detalhes específica da notificação
                }
            )
        }
        composable(Routes.City) {
            CityScreen(
                onContinue = { nav.navigate(Routes.Home) },
                onBack = { nav.popBackStack() },
                onDeviceLocationClick = {
                    // Condição simulada: se localização disponível, vai para Home
                    val locationAvailable = true // depois você troca por lógica real

                    if (locationAvailable) {
                        nav.navigate(Routes.Home)
                    } else {
                        // Aqui você pode mostrar um Toast, Snackbar ou logar
                        println("Localização não disponível")
                    }
                })
        }
        composable(Routes.Home) {
            // Certifique-se de que a variável 'nav' (NavController) está acessível neste escopo
            HomeScreen(
                // Ações de serviço existentes e topo de tela
                onFuelClick = {nav.navigate(Routes.Fuel) },
                onMaintenanceClick = { nav.navigate(Routes.Maintenance) },
                onSupportClick = { nav.navigate(Routes.Support) },

                // AÇÕES DO TOPO QUE ESTAVAM EM COMENTÁRIO/FALTANDO:
                onSearchClick = {/* nav.navigate(Routes.Search) */}, // Mude de /* comentário */ para a rota
                onBack = { nav.popBackStack() },
                onNotificationClick = { nav.navigate(Routes.Notifications) },

                // PARAMETRO QUE ESTÁ CAUSANDO O ERRO AGORA:
                onProfileClick = { nav.navigate(Routes.Profile) },

                // AÇÕES DA BARRA INFERIOR (já corrigidas)
                onRecordsClick = { nav.navigate(Routes.History) },
                onProfileNavClick = { nav.navigate(Routes.Profile) },
                onSupportNavClick = { nav.navigate(Routes.Support) },
                onArticleClick = { route -> nav.navigate(route) }
            )

        }

        composable(Routes.Profile) {
            ProfileScreen(
                onBack = { nav.popBackStack() },

                // Ação de Logout (mantida como você definiu)
                onLogout = {
                    nav.navigate(Routes.Welcome) {
                        popUpTo(Routes.Welcome) { inclusive = true }
                    }
                },

                // NOVOS PARÂMETROS NECESSÁRIOS:
                onCarDetailsClick = { nav.navigate(Routes.CarDetails) }, // Detalhes do Seu Carro
                onHistoryClick = { nav.navigate(Routes.History) },       // Comprovantes / Reembolsos
                onShareProfileClick = { nav.navigate(Routes.ShareProfile) }, // Compartilhar Perfil
                onPersonalizationClick = { nav.navigate(Routes.EditProfile) }, // Personalização
                onEditProfileClick = { nav.navigate(Routes.EditProfile) } // Lápis de Edição
            )
        }
        composable(Routes.Fuel) {
            FuelScreen(
                onBack = { nav.popBackStack() },
                onConfirm = { _, _, _ -> nav.popBackStack() } // Simplesmente volta
            )
        }
        composable(Routes.ArticleAbastecimento) {
            ArticleScreen(
                onBack = { nav.popBackStack() }, // Permite voltar para a tela anterior
                title = "Registro de Abastecimento",
                imageResId = R.drawable.abastecimento, // ID do seu drawable
                content = "Para registrar a nota de abastecimento, siga os passos no aplicativo. É crucial tirar uma foto legível do cupom fiscal, garantindo que o CNPJ do posto, a data e o valor total estejam visíveis. O sistema processará a imagem e salvará o comprovante."
            )
        }

// 2. Artigo: Guia de Manutenções
        composable(Routes.ArticleManutencao) {
            ArticleScreen(
                onBack = { nav.popBackStack() },
                title = "Guia de Manutenções",
                imageResId = R.drawable.manutencao, // ID do seu drawable
                content = "As manutenções preventivas devem ser agendadas a cada 10.000 km rodados ou a cada 6 meses, o que ocorrer primeiro. O aplicativo notificará você quando a próxima manutenção estiver próxima. "
            )
        }

// 3. Artigo: O que fazer em caso de Erros
        composable(Routes.ArticleErros) {
            ArticleScreen(
                onBack = { nav.popBackStack() },
                title = "O que fazer em caso de Erros",
                imageResId = R.drawable.erro, // ID do seu drawable
                content = "Se você encontrar um erro no registro de um comprovante, acesse a seção 'Ajuda' e inicie um chat com o suporte. O atendimento é rápido e a equipe pode corrigir dados."
            )
        }

        // ROTA DE FATURAS (Lista) - Usando Routes.Invoice
        composable(Routes.Invoice) {
            InvoiceScreen(
                onBack = { nav.popBackStack() },
                // onInvoiceClick leva para a tela de detalhes/confirmação
                onInvoiceClick = { invoiceId -> nav.navigate(Routes.InvoiceConfirm) }, // Rotas.InvoiceConfirm sem parâmetro de ID no momento
                // onPayClick leva para a tela de confirmação (aqui passamos o ID da fatura)
                onPayClick = { invoiceId -> nav.navigate(Routes.InvoiceConfirm) }
            )
        }

        // ROTA DE CONFIRMAÇÃO DE FATURAS - Usando Routes.InvoiceConfirm
        composable(route = Routes.InvoiceConfirm) {
            InvoiceConfirmScreen(
                onBack = { nav.popBackStack() },
                // CORRIGIDO: popUpTo usa Routes.Home (a constante certa)
                onPaymentConfirmed = { nav.navigate(Routes.Home) {
                    popUpTo(Routes.Home) { inclusive = true } // Limpa a pilha e volta para Home
                }}
            )
        }

        // Rota de Detalhes do Carro
        composable(Routes.CarDetails) {
            CarDetailsScreen(
                onBack = { nav.popBackStack() },
                onMaintenanceClick = { nav.navigate(Routes.Maintenance) },
                onInvoiceClick = { nav.navigate(Routes.Invoice) }
            )
        }
        // REMOVIDO: Linhas duplicadas de Invoice e InvoiceConfirm que causavam erros.
        // REMOVIDO: Linha 'composable(Routes.Invoice)' antiga com parâmetro 'onSent' errado.
        // REMOVIDO: Linha 'composable(Routes.InvoiceConfirm)' antiga com parâmetro 'onBackHome' errado.

        composable(Routes.Maintenance) {
            MaintenanceScreen(
                onBack = { nav.popBackStack() },
                onConfirm = { service -> nav.navigate(Routes.InvoiceConfirm) } // Passa o serviço, mas navega para a tela de Fatura Confirm
            )
        }
        composable(Routes.History) { HistoryScreen(onBack = { nav.popBackStack() }) }
        composable(Routes.Support) {
            SupportScreen(
                onBack = { nav.popBackStack() },
                onFaqClick = { nav.navigate(Routes.Faq) },
                onChatClick = { nav.navigate(Routes.Chat) },
                onCallClick = { /* Lógica para abrir o discador do celular */ }
            )
        }
        composable(Routes.Chat) { // Certifique-se que Routes.Chat existe no seu objeto Routes
            ChatScreen(
                onBack = { nav.popBackStack() }
            )
        }

        composable(Routes.Faq) { // Certifique-se que Routes.Faq existe no seu objeto Routes
            FaqScreen(
                onBack = { nav.popBackStack() }
            )
        }
    }
}