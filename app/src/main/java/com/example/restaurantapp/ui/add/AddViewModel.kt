package com.example.restaurantapp.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.MakananRepository
import com.example.restaurantapp.ui.AddMakanan
import com.example.restaurantapp.ui.AddUIState
import com.example.restaurantapp.ui.toAdmin

class AddViewModel(private val makananRepository: MakananRepository) : ViewModel() {
    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddMakanan) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addMakanan() {
        makananRepository.save(addUIState.addEvent.toAdmin())
    }
}


