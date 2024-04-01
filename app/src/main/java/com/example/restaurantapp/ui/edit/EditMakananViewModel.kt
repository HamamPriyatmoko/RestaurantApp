package com.example.restaurantapp.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.data.MakananRepository
import com.example.restaurantapp.ui.AddMakanan
import com.example.restaurantapp.ui.AddUIState
import com.example.restaurantapp.ui.toAdmin
import com.example.restaurantapp.ui.toUIStateAdmin
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMakananViewModel (
    savedStateHandle: SavedStateHandle,
    private val makananrepo : MakananRepository
): ViewModel() {
    var makanUiState by mutableStateOf(AddUIState())
        private set

    private  val makananId: String = checkNotNull(savedStateHandle[EditMakananScreen.makananId])

    init {
        viewModelScope.launch {
            makanUiState =
                makananrepo.getMakananById(makananId)
                    .filterNotNull()
                    .first()
                    .toUIStateAdmin()
        }
    }

    fun updateUiState(addEvent: AddMakanan){
        makanUiState = makanUiState.copy(addEvent = addEvent)
    }
    suspend fun updatemakanan(){
        makananrepo.update(makanUiState.addEvent.toAdmin())
    }
}