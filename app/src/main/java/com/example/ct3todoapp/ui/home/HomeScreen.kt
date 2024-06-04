package com.example.ct3todoapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ct3todoapp.data.ToDo
import com.example.ct3todoapp.ui.AppViewModelProvider
import com.example.ct3todoapp.ui.theme.CT3ToDoAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: () -> Unit,
    repository: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
                 CenterAlignedTopAppBar(title = { Text(text = "To Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigate) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        }
    ) {innerPadding ->
        val todos = repository.todos.collectAsState(initial = emptyList())
        val coroutineScope = rememberCoroutineScope()

        if (todos.value.isEmpty()) {
            Text(
                text = "Your List is Currently Empty"
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                items(todos.value) { item ->

                    ToDoCard(
                        toDo = item,
                        modifier = Modifier,
                        repository = repository,
                        onButtonClick = {
                            coroutineScope.launch {
                                repository.deleteTask(item)
                            }
                        },
                        coroutineScope = coroutineScope

                    )
                }
            }
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun ToDoCard(
    toDo: ToDo,
    modifier: Modifier,
    repository: HomeViewModel,
    onButtonClick: () -> Unit,
    coroutineScope: CoroutineScope
) {
    var checkedState by mutableStateOf(toDo.isComplete)
    fun onCheck(isChecked: Boolean) {
        toDo.isComplete = isChecked
        checkedState = toDo.isComplete
        coroutineScope.launch {
            repository.updateTask(toDo)
        }
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = ::onCheck,
        )
        Text(text = toDo.task)
        Spacer( modifier = modifier.weight(1f))
        Button(
            onClick = onButtonClick
        ) {
            Text("Delete")
        }
    }

}

@Preview
@Composable
fun AppPreview() {
    CT3ToDoAppTheme {
        HomeScreen(
            onNavigate = {}
        )
    }
}