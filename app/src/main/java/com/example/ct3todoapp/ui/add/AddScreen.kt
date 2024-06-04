package com.example.ct3todoapp.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ct3todoapp.ui.AppViewModelProvider
import com.example.ct3todoapp.ui.theme.CT3ToDoAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onPopBackStack: () -> Unit,
    repository: AddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Add a New Task")},
                navigationIcon = {
                    IconButton(onClick = onPopBackStack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack")
                    }
                }
            )
        }
    ) {innerPadding ->
        val coroutineScope = rememberCoroutineScope()
        ToDoForm(
            onSaveClick = {
                coroutineScope.launch {
                    repository.insert()
                    onPopBackStack()
                }
            },
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            repository = repository
        )

    }
}


@Composable
fun ToDoForm(
    onSaveClick: () -> Unit,
    modifier: Modifier,
    repository: AddViewModel
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            label = {Text("Task")},
            value = repository.taskDetails,
            onValueChange = {repository.taskDetails = it}
        )
        Button(
            onClick = onSaveClick,
            enabled = repository.taskDetails.isNotBlank()

        ) {
            Text(text = "Save")
        }
    }
}

@Preview
@Composable
fun AddPreview() {
    CT3ToDoAppTheme {
        AddScreen(
            onPopBackStack = {}
        )
    }
}