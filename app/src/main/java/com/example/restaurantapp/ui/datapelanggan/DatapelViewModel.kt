package com.example.restaurantapp.ui.datapelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.PelangganRepository
import com.example.restaurantapp.ui.AddPelanggan
import com.example.restaurantapp.ui.AddUIState
import com.example.restaurantapp.ui.toPelanggan

class DetailDatapelViewModel(private val pelangganRepository: PelangganRepository): ViewModel(){
    var AddUiState by mutableStateOf(AddUIState())
        private set

    fun UpdateAddUIState(addPelanggan: AddPelanggan) {
        AddUiState = AddUIState(addPelanggan = addPelanggan)
    }

    suspend fun addpelanggan() {
        pelangganRepository.save(AddUiState.addPelanggan.toPelanggan())
    }
}