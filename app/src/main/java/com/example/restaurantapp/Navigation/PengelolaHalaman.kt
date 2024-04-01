package com.example.restaurantapp.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurantapp.LoginPage
import com.example.restaurantapp.ui.AnimatedSplashScreen
import com.example.restaurantapp.ui.add.AddMenu
import com.example.restaurantapp.ui.add.TambahMenu
import com.example.restaurantapp.ui.datapelanggan.DataPel
import com.example.restaurantapp.ui.datapelanggan.DestinasiDataPel
import com.example.restaurantapp.ui.detail.DetailDestinationScreen
import com.example.restaurantapp.ui.detail.DetailScreen
import com.example.restaurantapp.ui.edit.EditMakanan
import com.example.restaurantapp.ui.edit.EditMakananScreen
import com.example.restaurantapp.ui.menuviewmodel.DetailPaymentScreen
import com.example.restaurantapp.ui.menuviewmodel.MenuScreen
import com.example.restaurantapp.ui.menuviewmodel.PaymentScreen
import com.example.restaurantapp.ui.menuviewmodel.TampilScreenMenu

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable("LoginPage") {
            LoginPage(navController)
        }
        composable(route = Screen.Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = DetailDestinationScreen.routeWithArgs,
            arguments = listOf(navArgument(DetailDestinationScreen.MakananId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(DetailDestinationScreen.MakananId)
            makananId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditMakananScreen.route}/$makananId")
                        println("makananId: $makananId")
                    }
                )
            }
        }
        composable(DestinasiDataPel.route) {
            DataPel(
                navigateBack = { navController.navigate("LoginPage") },
                navigateNext = { navController.navigate((TampilScreenMenu.route)) }
            )
        }
        composable(
            route = EditMakananScreen.routeWithArgs,
            arguments = listOf(navArgument(EditMakananScreen.makananId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(EditMakananScreen.makananId)
            makananId?.let {
                EditMakanan(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() })
            }
        }
        composable(
            TampilScreenMenu.route
        ) {
            MenuScreen(
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestinationScreen.route}/$itemId")
                    println("itemId: $itemId")
                },
                onPaymentClick = { itemId ->
                    navController.navigate("${DetailPaymentScreen.route}/$itemId")
                },
                navigateToItemEntry = {
                    navController.navigate(TambahMenu.route)
                },
            )
        }
        composable(TambahMenu.route) {
            AddMenu(
                navigateBack = { navController.popBackStack() })
        }
        composable(
            route = DetailPaymentScreen.routeWithArgs,
            arguments = listOf(navArgument(DetailPaymentScreen.MakananId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(DetailPaymentScreen.MakananId)
            makananId?.let {
                PaymentScreen(
                    navigateBack = { navController.popBackStack() },
                )
            }
        }
    }
}