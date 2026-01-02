package com.example.questfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.questfirebase.modeldata.DetailSiswa
import com.example.questfirebase.modeldata.UIStateSiswa
import com.example.questfirebase.modeldata.toDataSiswa
import com.example.questfirebase.repositori.RepositorySiswa

class EntryViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    /* Variabel untuk menyimpan state UI */
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Fungsi untuk memvalidasi input apakah sudah terisi semua */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    /* Fungsi untuk memperbarui state saat user mengetik di TextField */
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    /* Fungsi suspend untuk menyimpan data ke Firebase melalui Repositori */
    suspend fun addSiswa() {
        if (validasiInput()) {
            repositorySiswa.postDataSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}