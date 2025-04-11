package com.bosta.shoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bosta.shoppinglist.data.Category
import com.bosta.shoppinglist.ui.screen.CategoryDetails
import com.bosta.shoppinglist.viewModel.MainViewModel
import com.bosta.shoppinglist.ui.screen.recipeScreen

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState
    NavHost(navController= navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            recipeScreen(recipeState = viewState,navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:Category("","","","")
            CategoryDetails(category)
        }
    }
}