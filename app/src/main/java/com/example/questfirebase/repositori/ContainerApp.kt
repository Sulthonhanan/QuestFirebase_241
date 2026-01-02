package com.example.questfirebase.repositori

// Menggunakan interface dan implementasi dari file RepositoriSiswa.kt
interface AppContainer {
    val repositorySiswa: RepositoriSiswa
}

class ContainerApp : AppContainer {
    override val repositorySiswa: RepositoriSiswa by lazy {
        FirebaseRepositoriSiswa()
    }
}