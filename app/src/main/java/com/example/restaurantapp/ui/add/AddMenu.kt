package com.example.restaurantapp.ui.add

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantapp.Navigation.DestinasiNavigasi
import com.example.restaurantapp.ui.AddMakanan
import com.example.restaurantapp.ui.AddMenuTopAppBar
import com.example.restaurantapp.ui.AddUIState
import com.example.restaurantapp.ui.PenyediaViewModel
import kotlinx.coroutines.launch

object TambahMenu: DestinasiNavigasi {
    override val route = "item_masuk"
    override val titleRes = "add menu"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenu(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),
    ) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AddMenuTopAppBar(
                title = TambahMenu.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            addUIState = addViewModel.addUIState,
            onDataValueChange = addViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel.addMakanan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    addUIState: AddUIState,
    onDataValueChange: (AddMakanan) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            addEvent = addUIState.addEvent,
            onValueChange = onDataValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun FormInput(
    addEvent: AddMakanan,
    modifier: Modifier = Modifier,
    onValueChange: (AddMakanan) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addEvent.nama,
            onValueChange = { onValueChange(addEvent.copy(nama = it)) },
            label = { Text("Nama Makanan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.Harga,
            onValueChange = { onValueChange(addEvent.copy(Harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.Jenis,
            onValueChange = { onValueChange(addEvent.copy(Jenis = it)) },
            label = { Text("Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}