package com.example.amemiyaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amemiyaapp.navigation.Routes.CarDetails
import com.example.amemiyaapp.ui.screens.*

object Routes {
    const val Welcome = "welcome"
    const val SignUp = "signup"
    const val Login = "login"
    const val Verify = "verify"
    const val City = "city"
    const val Home = "home"
    const val Profile = "profile"
    const val CarDetails = "car_details"
    const val Invoice = "invoice"
    const val InvoiceConfirm = "invoice_confirm"
    const val Maintenance = "maintenance"
    const val History = "history"
    const val Support = "support"
}

@Composable
fun AppNavHost() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.Welcome) {
        composable(Routes.Welcome) { WelcomeScreen(onNext = { nav.navigate(Routes.SignUp) }) }
        composable(Routes.SignUp) { SignUpScreen(onLoginClick = { nav.navigate(Routes.Login) }, onBack = { nav.popBackStack() })}
        composable(Routes.Login) { LoginScreen(onSignUpClick = { nav.navigate(Routes.SignUp) }, onGetCode = { nav.navigate(Routes.Verify) }, onBack = { nav.popBackStack() }) }
        composable(Routes.Verify) { VerifyScreen(onVerified = { nav.navigate(Routes.City) },  onResendCodeClick = { println("Código reenviado!")} , onBack = { nav.popBackStack() }) }
        composable(Routes.City) { CityScreen(onContinue = { nav.navigate(Routes.Home) }, onBack = { nav.popBackStack() },onDeviceLocationClick = {
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
            HomeScreen(
                // Mapeando suas ações originais:
                onProfileClick = { nav.navigate(Routes.Profile) }, // Perfil (Avatar/Topo)
                onSupportClick = { nav.navigate(Routes.Support) }, // Suporte (Card de Serviço)

                // Usando o onSupportClick para as ações que não têm uma rota específica definida na sua chamada original:
                onFuelClick = { nav.navigate(Routes.Invoice) },          // Abastecimento
                onMaintenanceClick = { nav.navigate(Routes.Maintenance) }, // Manutenção

                // Ações adicionais necessárias pela nova HomeScreen:
                onSearchClick = { /* Ação de busca, ou nav.navigate(Routes.Search) */ },
                onBack = { nav.popBackStack() },                      // Ação da seta de voltar
                onNotificationClick = { /* Ação de notificação, ou nav.navigate(Routes.Notifications) */ }
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
                onCarDetailsClick = { nav.navigate(CarDetails) }, // Detalhes do Seu Carro
                onReceiptsClick = { nav.navigate(Routes.CarDetails) },       // Comprovantes / Reembolsos
                onShareProfileClick = { nav.navigate(Routes.CarDetails) }, // Compartilhar Perfil
                onPersonalizationClick = { nav.navigate(Routes.CarDetails) }, // Personalização
                onEditProfileClick = { nav.navigate(Routes.CarDetails) } // Lápis de Edição
            )//tenho que arrumar essa parte
        }
        composable(CarDetails) { CarDetailsScreen(onBack = { nav.popBackStack() }, onMaintenanceClick = { nav.navigate(Routes.Maintenance) }, onInvoiceClick = { nav.navigate(Routes.Invoice) }) }
        composable(Routes.Invoice) { InvoiceScreen(onBack = { nav.popBackStack() }, onSent = { nav.navigate(Routes.InvoiceConfirm) }) }
        composable(Routes.InvoiceConfirm) { InvoiceConfirmScreen(onBackHome = { nav.navigate(Routes.Home) { popUpTo(Routes.Home) { inclusive = true } } }) }
        composable(Routes.Maintenance) { MaintenanceScreen(onBack = { nav.popBackStack() }, onConfirm = { nav.navigate(Routes.InvoiceConfirm) }) }
        composable(Routes.History) { HistoryScreen(onBack = { nav.popBackStack() }) }
        composable(Routes.Support) { SupportScreen(onBack = { nav.popBackStack() }) }
    }
}




