package com.bosta.shoppinglist.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.shoppinglist.data.Category
import com.bosta.shoppinglist.services.recipeService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState : State<RecipeState> = _categoryState
    init{
        fetchCategories()
    }

    private fun fetchCategories () {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            } catch (e:Exception){
                _categoryState.value.copy(
                    loading = false,
                    error = "Error happened while fetching the data $e"
                )
            }
        }
    }
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null,
    )

}