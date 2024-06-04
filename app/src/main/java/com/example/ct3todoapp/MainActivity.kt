package com.example.ct3todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ct3todoapp.ui.add.AddScreen
import com.example.ct3todoapp.ui.add.AddViewModel
import com.example.ct3todoapp.ui.home.HomeScreen
import com.example.ct3todoapp.ui.home.HomeViewModel
import com.example.ct3todoapp.ui.theme.CT3ToDoAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CT3ToDoAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(
                            onNavigate = { navController.navigate(Routes.ADD) }
                        )
                    }
                    composable(Routes.ADD) {
                        AddScreen(
                            onPopBackStack = { navController.popBackStack() },
                        )
                    }
                }
            }
        }
    }
}

