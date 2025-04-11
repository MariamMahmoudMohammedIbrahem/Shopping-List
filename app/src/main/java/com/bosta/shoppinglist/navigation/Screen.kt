package com.bosta.shoppinglist.navigation

sealed class Screen (val route: String) {
    object RecipeScreen : Screen(route = "recipeScreen")
    object DetailScreen : Screen(route = "detailScreen")
}