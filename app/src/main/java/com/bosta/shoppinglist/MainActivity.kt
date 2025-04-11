package com.bosta.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bosta.shoppinglist.navigation.RecipeApp
import com.bosta.shoppinglist.navigation.firstScreen
import com.bosta.shoppinglist.navigation.secondScreen
import com.bosta.shoppinglist.ui.screen.recipeScreen
import com.bosta.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val  navController = rememberNavController()
            ShoppingListTheme {
                RecipeApp(navController)
            }
        }
    }
}
