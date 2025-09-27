package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.ui.theme.Project1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme {
                Scaffold { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) { // Wrap content in a Column and apply padding
                        MyForm() // MyForm will use its own internal padding
                        Greeting("Android")
                    }
                }
                // Ctrl + Alt + L for auto format code
                // To enable auto refresh when developing,
                // go to File > Settings > Editor > Live Edit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // For Material 3 components like TextField
@Composable
fun MyTextInputField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done // Or ImeAction.Next for forms
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = modifier) {
        OutlinedTextField( // Or TextField for a filled style
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            isError = isError,
            supportingText = {
                if (isError && errorMessage != null) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
        )
    }
}

@Composable
fun MyForm(modifier: Modifier = Modifier) { // modifier parameter is kept for flexibility
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Apply the passed modifier (if any) and chain existing padding
    Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 36.dp)) {
        MyTextInputField(
            label = "Name",
            value = name,
            onValueChange = {
                name = it
                nameError = if (it.length < 3 && it.isNotEmpty()) "Name is too short" else null
            },
            placeholder = "Enter your full name",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            isError = nameError != null,
            errorMessage = nameError
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            keyboardController?.hide()
            // Validate and process the data
            val ageInt = age.toIntOrNull()
            val priceDouble = price.toDoubleOrNull()
            println("Submit: Name=$name, Age=$ageInt, Price=$priceDouble")
        }) {
            Text("Submit")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(20.dp) // This padding is in addition to what its parent Column provides
            .clip(RoundedCornerShape(16.dp))
    ) {
        Surface(color = Color.Red) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(24.dp),
                fontSize = 25.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project1Theme {
        MyForm()
    }
}
