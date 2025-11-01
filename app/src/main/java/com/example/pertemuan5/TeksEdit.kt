package com.example.pertemuan5

import android.app.DatePickerDialog
import java.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormRegistrasi(modifier: Modifier = Modifier) {

    var textNama by remember { mutableStateOf("") }
    var textalamat by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var textrt by remember { mutableStateOf("") }
    var textrw by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    var textumur by remember { mutableStateOf("") }
    var setuju by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var tgl by remember { mutableStateOf("") }
    var rt by remember { mutableStateOf("") }
    var rw by remember { mutableStateOf("") }
    var umur by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }

    val gender: List<String> = listOf("Laki-laki", "Perempuan")
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tanggal = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE8F5E9), Color(0xFFC8E6C9), Color.White)
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.9f))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Form Registrasi",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 28.sp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = textNama,
                onValueChange = { textNama = it },
                label = { Text("Nama Lengkap") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = textalamat,
                onValueChange = { textalamat = it },
                label = { Text("Alamat Asal") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tanggal Lahir") },
                    modifier = Modifier
                        .weight(1f)
                        .focusable(false)
                        .clickable {
                            datePickerDialog.show()
                        }
                )
                OutlinedTextField(
                    value = textrt,
                    onValueChange = { textrt = it.filter { char -> char.isDigit() } },
                    label = { Text("RT") },
                    singleLine = true,
                    modifier = Modifier.width(60.dp)
                )
                OutlinedTextField(
                    value = textrw,
                    onValueChange = { textrw = it.filter { char -> char.isDigit() } },
                    label = { Text("RW") },
                    singleLine = true,
                    modifier = Modifier.width(60.dp)
                )
            }
            OutlinedTextField(
                value = umur,
                onValueChange = { umur = it.filter { char -> char.isDigit() }},
                label = { Text("Umur") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Jenis Kelamin",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    gender.forEach { item ->
                        Row(
                            Modifier.selectable(
                                selected = textJK == item,
                                onClick = { textJK = item }
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = textJK == item,
                                onClick = { textJK = item }
                            )
                            Text(item)
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp)
            ) {
                Checkbox(
                    checked = setuju,
                    onCheckedChange = { setuju = it }
                )
                Text(
                    text = "Saya setuju dengan syarat dan ketentuan yang berlaku",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
            Button (
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                enabled = textalamat.isNotEmpty() &&
                        textNama.isNotEmpty() &&
                        textJK.isNotEmpty() &&
                        setuju,
                onClick = {
                    nama = textNama
                    alamat = textalamat
                    rt = textrt
                    rw = textrw
                    umur = textumur
                    jenis = textJK

                    showDialog = true
                }
            ){
                Text(stringResource(R.string.submit))
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Data Registrasi Berhasil") },
                text = {
                    Column {
                        Text("Nama Lengkap: ${nama}")
                        Text("Kota Asal: ${alamat}")
                        Text("Tanggal Lahir: ${tgl}")
                        Text("RT/RW: ${rt}/${rw}")
                        Text("Umur: ${umur}")
                        Text("Jenis Kelamin: ${jenis}")
                    }
                },
                containerColor = Color(0xFFF1F8E9)
            )
        }
    }
}