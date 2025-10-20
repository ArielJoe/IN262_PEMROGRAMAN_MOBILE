package com.example.exercise_05

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Screen {
    Home,
    InputPasien,
    ListPasien
}

data class Pasien(
    val nama: String,
    val tanggalLahir: String,
    val jenisKelamin: String,
    val memilikiDisabilitas: Boolean,
    val asuransi: String,
    val alamat: String,
    val email: String
)

val daftarAsuransi = listOf(
    "BPJS Kesehatan",
    "Prudential",
    "Allianz",
    "AXA Mandiri",
    "Tidak Ada"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    var daftarPasien by remember { mutableStateOf<List<Pasien>>(emptyList()) }
    var pasienBaru by remember {
        mutableStateOf(
            Pasien("", "", "Laki-laki", false, daftarAsuransi[0], "", "")
        )
    }
    val context = LocalContext.current

    when (currentScreen) {
        Screen.Home -> HomeScreen(
            onInputClick = { currentScreen = Screen.InputPasien },
            onViewListClick = { currentScreen = Screen.ListPasien }
        )

        Screen.InputPasien -> InputPasienScreen(
            pasien = pasienBaru,
            daftarAsuransi = daftarAsuransi,
            onPasienChange = { pasienBaru = it },
            onSimpan = { pasien ->
                if (pasien.nama.isBlank() || pasien.email.isBlank()) {
                    Toast.makeText(context, "Nama dan email wajib diisi!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    daftarPasien = daftarPasien + pasien
                    currentScreen = Screen.ListPasien
                    Toast.makeText(context, "Data pasien tersimpan!", Toast.LENGTH_SHORT).show()
                }
            },
            onBack = { currentScreen = Screen.Home },
            context = context
        )

        Screen.ListPasien -> ListPasienScreen(
            pasienList = daftarPasien,
            onBack = { currentScreen = Screen.Home }
        )
    }
}

@Composable
fun HomeScreen(
    onInputClick: () -> Unit,
    onViewListClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Aplikasi Data Pasien", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))
        Button(onClick = onInputClick, modifier = Modifier.fillMaxWidth()) {
            Text("Input Data Pasien")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onViewListClick, modifier = Modifier.fillMaxWidth()) {
            Text("Lihat Daftar Pasien")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPasienScreen(
    pasien: Pasien,
    daftarAsuransi: List<String>,
    onPasienChange: (Pasien) -> Unit,
    onSimpan: (Pasien) -> Unit,
    onBack: () -> Unit,
    context: Context
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Form Input Pasien", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = pasien.nama,
            onValueChange = { onPasienChange(pasien.copy(nama = it)) },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pasien.tanggalLahir,
            onValueChange = { onPasienChange(pasien.copy(tanggalLahir = it)) },
            label = { Text("Tanggal Lahir (dd/MM/yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Jenis Kelamin", modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = pasien.jenisKelamin == "Laki-laki",
                onClick = { onPasienChange(pasien.copy(jenisKelamin = "Laki-laki")) }
            )
            Text("Laki-laki", modifier = Modifier.padding(end = 16.dp))
            RadioButton(
                selected = pasien.jenisKelamin == "Perempuan",
                onClick = { onPasienChange(pasien.copy(jenisKelamin = "Perempuan")) }
            )
            Text("Perempuan")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = pasien.memilikiDisabilitas,
                onCheckedChange = { onPasienChange(pasien.copy(memilikiDisabilitas = it)) }
            )
            Text("Memiliki disabilitas?")
        }

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = pasien.asuransi,
                onValueChange = {},
                readOnly = true,
                label = { Text("Asuransi") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                daftarAsuransi.forEach { pilihan ->
                    DropdownMenuItem(
                        text = { Text(pilihan) },
                        onClick = {
                            onPasienChange(pasien.copy(asuransi = pilihan))
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pasien.alamat,
            onValueChange = { onPasienChange(pasien.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pasien.email,
            onValueChange = { onPasienChange(pasien.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = onBack, modifier = Modifier.weight(1f)) {
                Text("Kembali")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onSimpan(pasien) }, modifier = Modifier.weight(1f)) {
                Text("Simpan")
            }
        }
    }
}

@Composable
fun ListPasienScreen(
    pasienList: List<Pasien>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Daftar Pasien", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        if (pasienList.isEmpty()) {
            Text(
                "Belum ada data pasien.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(pasienList) { pasien ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Nama: ${pasien.nama}", fontWeight = FontWeight.Bold)
                            Text("Lahir: ${pasien.tanggalLahir}")
                            Text("Jenis Kelamin: ${pasien.jenisKelamin}")
                            Text("Disabilitas: ${if (pasien.memilikiDisabilitas) "Ya" else "Tidak"}")
                            Text("Asuransi: ${pasien.asuransi}")
                            Text("Alamat: ${pasien.alamat}")
                            Text("Email: ${pasien.email}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali ke Beranda")
        }
    }
}