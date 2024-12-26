package eg.test.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eg.test.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    CaptainGame()
//                    UnitConverter()
                    ShoppingList()
                }
            }
        }
    }


    @Composable
    fun CaptainGame(){
//        val treasureFound = remember{ mutableStateOf(0) }
        val treasureFound by remember{ mutableStateOf(0) }
        val direction = remember{ mutableStateOf("North") }
        val stormOrTreasure = remember{ mutableStateOf("") }
        Text(text = "Treasures Found: $treasureFound")
        Text(text = "Current Direction: ${direction.value}")
        Text(text = stormOrTreasure.value)
        Button(onClick = {
            direction.value = "East"
            if(Random.nextBoolean()){
                treasureFound.inc()
                stormOrTreasure.value = "Found a Treasure!"
            }else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "East")
        }
        Button(onClick = {
            direction.value = "West"
            if(Random.nextBoolean()){
                treasureFound.inc()
                stormOrTreasure.value = "Found a Treasure!"
            }else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "West")
        }
        Button(onClick = {
            direction.value = "North"
            if(Random.nextBoolean()){
                treasureFound.inc()
                stormOrTreasure.value = "Found a Treasure!"
            }else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "North")
        }
        Button(onClick = {
            direction.value = "South"
            if(Random.nextBoolean()){
                treasureFound.inc()
                stormOrTreasure.value = "Found a Treasure!"
            }else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "South")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UnitConverter(){
        var iExpand by remember{ mutableStateOf(false) }
        var oExpand by remember{ mutableStateOf(false) }
        var inputValue by remember {
            mutableStateOf("")
        }
        var inputType by remember {
            mutableStateOf("Meter")
        }
        var outputValue by remember {
            mutableStateOf("")
        }
        var outputType by remember {
            mutableStateOf("Meter")
        }
        val conversionRatio = remember {
            mutableStateOf(1.0)
        }
        val oConversionRatio = remember {
            mutableStateOf(1.0)
        }
        val customTextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 16.sp,
            color = Color.Red
        )
//        val result by remember { mutableStateOf("") }
        fun convertUnits(){
            val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
            val result = (inputValueDouble * conversionRatio.value *100.0/oConversionRatio.value).roundToInt()/100.0
            outputValue = result.toString()
        }
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Unit Converter", style=customTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter Value")},
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box {
                    Button(onClick = {
                        iExpand = !iExpand
                    }) {
                        Text(inputType)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }
                    DropdownMenu(
                        expanded = iExpand,
                        onDismissRequest = { iExpand = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeter") },
                            onClick = {
                                inputType = "Centimeter"
                                iExpand = false
                                conversionRatio.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meter") },
                            onClick = {
                                inputType = "Meter"
                                iExpand = false
                                conversionRatio.value = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeter") },
                            onClick = {
                                inputType = "Millimeter"
                                iExpand = false
                                conversionRatio.value = 0.001
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                inputType = "Feet"
                                iExpand = false
                                conversionRatio.value = 0.3048
                                convertUnits()
                            }
                        )
                    }
                }
                Box {
                    Button(onClick = {
                        oExpand = !oExpand
                    }) {
                        Text(outputType)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeter") },
                            onClick = {
                                outputType = "Centimeter"
                                oExpand = false
                                oConversionRatio.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meter") },
                            onClick = {
                                outputType = "Meter"
                                oExpand = false
                                oConversionRatio.value = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeter") },
                            onClick = {
                                outputType = "Millimeter"
                                oExpand = false
                                oConversionRatio.value = 0.001
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                outputType = "Feet"
                                oExpand = false
                                oConversionRatio.value = 0.3048
                                convertUnits()
                            }
                        )
                    }
                }
            }
            Row {
                val context = LocalContext.current
                Button(onClick = { Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show() }) {
                    Text(text = "Click Me!")
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}*/
