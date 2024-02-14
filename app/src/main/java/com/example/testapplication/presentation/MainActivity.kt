package com.example.testapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapplication.presentation.filter_screen.FilterScreen
import com.example.testapplication.presentation.filter_screen.FilterViewModel
import com.example.testapplication.presentation.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel : FilterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilterScreen(viewModel = viewModel)
                }
            }
        }
    }
}
