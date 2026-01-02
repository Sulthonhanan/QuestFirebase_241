package com.example.questfirebase.viewmodel // Sesuaikan ke package viewmodel Anda

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.questfirebase.AplikasiDataSiswa // Import class Application Anda

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk HomeViewModel
        initializer {
            HomeViewModel(
                aplikasiDataSiswa().container.repositorySiswa
            )
        }
        // Initializer untuk EntryViewModel
        initializer {
            EntryViewModel(
                aplikasiDataSiswa().container.repositorySiswa
            )
        }
    }
}

// Fungsi ekstensi untuk mempermudah akses ke Application class
fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiDataSiswa)