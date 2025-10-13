package com.example.a2dshapecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Rectangle
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a2dshapecalculator.ui.theme._2DShapeCalculatorTheme
import java.util.Locale
import kotlin.math.sqrt

data class CalculationResult(val area: String, val perimeter: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2DShapeCalculatorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main",
                ) {
                    composable("main") {
                        ShapeCalculator(navController = navController, modifier = Modifier)
                    }
                    composable("square") {
                        SquareScreen(navController = navController)
                    }
                    composable("rectangle") {
                        RectangleScreen(navController = navController)
                    }
                    composable("triangle") {
                        TriangleScreen(navController = navController)
                    }
                    composable("circle") {
                        CircleScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Two Dimensional Shape Calculator",
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopAppBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun ShapeButton(
    text: String,
    icon: ImageVector,
    contentDescription: String,
    containerColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier
            .size(180.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(40.dp)
            )
            Text(text = text, modifier = Modifier.padding(top = 12.dp))
        }
    }
}

@Composable
fun ShapeCalculator(navController: NavController, modifier: Modifier) {
    Scaffold(
        topBar = { MainTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                ShapeButton(
                    text = "Square",
                    icon = Icons.Filled.Square,
                    contentDescription = "Square",
                    containerColor = Color(0xFF5F00EB),
                    onClick = { navController.navigate("square") }
                )
                ShapeButton(
                    text = "Rectangle",
                    icon = Icons.Filled.Rectangle,
                    contentDescription = "Rectangle",
                    containerColor = Color(0xFF00D7C3),
                    onClick = { navController.navigate("rectangle") }
                )
            }
            Row {
                ShapeButton(
                    text = "Triangle",
                    icon = Icons.Filled.PlayArrow,
                    contentDescription = "Triangle",
                    containerColor = Color(0xFFFF551E),
                    onClick = { navController.navigate("triangle") }
                )
                ShapeButton(
                    text = "Circle",
                    icon = Icons.Filled.Circle,
                    contentDescription = "Circle",
                    containerColor = Color(0xFF4BAF50),
                    onClick = { navController.navigate("circle") }
                )
            }
        }
    }
}

@Composable
fun ResultDisplay(
    area: String,
    perimeter: String,
    perimeterLabel: String,
    unitArea: String = "cm²",
    unitPerimeter: String = "cm"
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Area: $area $unitArea",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$perimeterLabel: $perimeter $unitPerimeter",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CalculatorScreen(
    navController: NavController,
    title: String,
    inputLabels: List<String>,
    perimeterLabel: String,
    unitArea: String = "cm²",
    unitPerimeter: String = "cm",
    calculation: (List<Double>) -> CalculationResult
) {
    val inputValues =
        remember { mutableStateListOf<String>().apply { addAll(List(inputLabels.size) { "" }) } }
    var area by remember { mutableStateOf("0.00") }
    var perimeter by remember { mutableStateOf("0.00") }

    LaunchedEffect(inputValues.toList()) {
        val doubleValues = inputValues.mapNotNull { it.toDoubleOrNull() }
        if (doubleValues.size == inputLabels.size) {
            val result = calculation(doubleValues)
            area = result.area
            perimeter = result.perimeter
        } else {
            area = "0.00"
            perimeter = "0.00"
        }
    }

    Scaffold(
        topBar = { ScreenTopAppBar(title = title, navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            inputLabels.forEachIndexed { index, label ->
                OutlinedTextField(
                    value = inputValues[index],
                    onValueChange = { inputValues[index] = it },
                    label = { Text("$label (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                        unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            ResultDisplay(
                area = area,
                perimeter = perimeter,
                perimeterLabel = perimeterLabel,
                unitArea = unitArea,
                unitPerimeter = unitPerimeter
            )
        }
    }
}

@Composable
fun SquareScreen(navController: NavController) {
    CalculatorScreen(
        navController = navController,
        title = "Square Calculator",
        inputLabels = listOf("Side Length"),
        perimeterLabel = "Perimeter",
        unitArea = "cm²",
        unitPerimeter = "cm",
        calculation = { values ->
            val side = values[0]
            val area = side * side
            val perimeter = 4 * side
            CalculationResult(
                String.format(Locale.US, "%.2f", area),
                String.format(Locale.US, "%.2f", perimeter)
            )
        }
    )
}

@Composable
fun RectangleScreen(navController: NavController) {
    CalculatorScreen(
        navController = navController,
        title = "Rectangle Calculator",
        inputLabels = listOf("Length", "Width"),
        perimeterLabel = "Perimeter",
        unitArea = "cm²",
        unitPerimeter = "cm",
        calculation = { values ->
            val length = values[0]
            val width = values[1]
            val area = length * width
            val perimeter = 2 * (length + width)
            CalculationResult(
                String.format(Locale.US, "%.2f", area),
                String.format(Locale.US, "%.2f", perimeter)
            )
        }
    )
}

@Composable
fun TriangleScreen(navController: NavController) {
    CalculatorScreen(
        navController = navController,
        title = "Triangle Calculator",
        inputLabels = listOf("Side A", "Side B", "Side C"),
        perimeterLabel = "Perimeter",
        unitArea = "cm²",
        unitPerimeter = "cm",
        calculation = { values ->
            val a = values[0]
            val b = values[1]
            val c = values[2]
            if (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a) {
                val perimeter = a + b + c
                val s = perimeter / 2.0
                val area = sqrt(s * (s - a) * (s - b) * (s - c))
                CalculationResult(
                    String.format(Locale.US, "%.2f", area),
                    String.format(Locale.US, "%.2f", perimeter)
                )
            } else {
                CalculationResult("Invalid Triangle", "N/A")
            }
        }
    )
}

@Composable
fun CircleScreen(navController: NavController) {
    CalculatorScreen(
        navController = navController,
        title = "Circle Calculator",
        inputLabels = listOf("Radius"),
        perimeterLabel = "Circumference",
        unitArea = "cm²",
        unitPerimeter = "cm",
        calculation = { values ->
            val radius = values[0]
            val area = Math.PI * radius * radius
            val circumference = 2 * Math.PI * radius
            CalculationResult(
                String.format(Locale.US, "%.2f", area),
                String.format(Locale.US, "%.2f", circumference)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _2DShapeCalculatorTheme {}
}

// 2372015 - Ariel