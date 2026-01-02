package com.example.questfirebase.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questfirebase.R
import com.example.questfirebase.modeldata.Siswa
import com.example.questfirebase.viewmodel.HomeViewModel
import com.example.questfirebase.viewmodel.PenyediaViewModel
import com.example.questfirebase.viewmodel.StatusUiSiswa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = "Daftar Siswa",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Siswa")
            }
        }
    ) { innerPadding ->
        HomeStatus(
            statusUiSiswa = viewModel.statusUiSiswa,
            retryAction = { viewModel.loadSiswa() },
            onDetailClick = onDetailClick,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeStatus(
    statusUiSiswa: StatusUiSiswa,
    retryAction: () -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (statusUiSiswa) {
        is StatusUiSiswa.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is StatusUiSiswa.Success -> SiswaLayout(
            listSiswa = statusUiSiswa.siswa,
            onDetailClick = onDetailClick,
            modifier = modifier.fillMaxWidth()
        )
        is StatusUiSiswa.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun SiswaLayout(
    listSiswa: List<Siswa>,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listSiswa) { siswa ->
            SiswaCard(
                siswa = siswa,
                modifier = Modifier.clickable { onDetailClick(siswa.id.toString()) }
            )
        }
    }
}

@Composable
fun SiswaCard(siswa: Siswa, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = siswa.nama, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                Text(text = siswa.telpon, style = MaterialTheme.typography.titleMedium)
            }
            Text(text = "Alamat: ${siswa.alamat}", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable fun OnLoading(modifier: Modifier = Modifier) { /* UI Loading */ }
@Composable fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) { /* UI Error */ }