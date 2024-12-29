package eg.test.unitconverter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ShoppingList(){
    var sItems by remember {
        mutableStateOf(listOf<ShoppingItem>())
    }
    var showAlertDialog by remember {
        mutableStateOf(false)
    }
    var name by remember {
        mutableStateOf("")
    }
    var quantity by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ){
        Button(onClick = {
            showAlertDialog = true
        }) {
            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            items(
                sItems
            ){
                ShoppingListItem(it, {},{})
            }
        }
    }
    if(showAlertDialog){
        AlertDialog(
            onDismissRequest = {
               showAlertDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if(name.isNotBlank()){
                            val newItem = ShoppingItem(sItems.size+1,name, quantity.toInt())
                            sItems += newItem
                            print(sItems)
//                            sItems.plus(ShoppingItem(sItems.size+1,name, quantity.toInt()))
                            showAlertDialog = false
                            name =""
                            quantity = ""
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAlertDialog = false
                    name =""
                    quantity = ""
                }) {
                    Text("Cancel")
                }
            },
            title = {Text("Add Item")},
            text = {
                Column {
                    Text(
                        text = "name"
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                    Text(
                        text = "quantity"
                    )
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = {
                            quantity = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
           },
        )
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick:()->Unit,
    onDeleteClick:()->Unit,
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    2.dp,
                    Color(0xFF018786),
                ),
                shape = RoundedCornerShape(20),
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
        IconButton(
            onClick = onEditClick,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Icon",
                tint = Color.Gray,
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Gray,
            )
        }
    }
}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete:(String,Int) -> Unit){
    var editedName by remember {
        mutableStateOf(item.name)
    }
    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = {editedName = it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange = {editedQuantity = it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }
        Button(
            onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toIntOrNull()?:1)
            }
        ) {

        }
    }
}
data class ShoppingItem(val id: Int, var name: String, var quantity: Int, var isEditing: Boolean = false)