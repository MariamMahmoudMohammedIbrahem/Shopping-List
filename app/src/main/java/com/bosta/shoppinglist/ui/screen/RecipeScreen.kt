package com.bosta.shoppinglist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.bosta.shoppinglist.data.Category
import com.bosta.shoppinglist.viewModel.MainViewModel

@Composable
fun recipeScreen(navigateToDetail:(Category)->Unit,
                 recipeState: MainViewModel.RecipeState){
//    val viewModel: MainViewModel = viewModel()
//    val recipeState by viewModel.categoryState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            recipeState.loading -> {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "check circle",
                    tint = Color.Red
                )
//                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            !recipeState.error.isNullOrBlank() -> {
                Text("error happened while loading data please try again")
            }
            else -> {
                // lazy column of grid view of items
                categoryScreen(categories = recipeState.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun categoryScreen (categories: List<Category>, navigateToDetail:(Category)->Unit){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxSize()){
        items(categories){
                category -> categoryItem(category = category, navigateToDetail = {
            navigateToDetail(
                category
            )
        } )
        }
    }
}
@Composable
fun categoryItem (category: Category, navigateToDetail:(Category) -> Unit) {
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp)
            .clickable {
                       navigateToDetail(category)
//                rememberNavController().navigate(route = "categoryDetails")
                       },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}