package com.example.restaurantapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.restaurantapp.MakananAplication
import com.example.restaurantapp.ui.add.AddViewModel
import com.example.restaurantapp.ui.datapelanggan.DetailDatapelViewModel
import com.example.restaurantapp.ui.detail.DetailViewModel
import com.example.restaurantapp.ui.edit.EditMakananViewModel
import com.example.restaurantapp.ui.menuviewmodel.MenuViewModel

fun CreationExtras.apkikasiMakanan(): MakananAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MakananAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            DetailDatapelViewModel(apkikasiMakanan().container.pelangganRepository)
        }
        initializer {
            MenuViewModel(apkikasiMakanan().container.makananRepository)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                apkikasiMakanan().container.makananRepository
            )
        }
        initializer {
            AddViewModel(apkikasiMakanan().container.makananRepository)
        }
        initializer {
            EditMakananViewModel(
                createSavedStateHandle(),
                apkikasiMakanan().container.makananRepository
            )
        }

    }
}