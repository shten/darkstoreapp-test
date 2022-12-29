package com.darkstoreapp.test

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darkstoreapp.test.features.calculator.widgets.CalculatorScreen
import com.darkstoreapp.test.features.common.AppTheme
import com.darkstoreapp.test.features.history.widgets.HistoryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_CALCULATOR
                ) {
                    composable(ROUTE_CALCULATOR) {
                        CalculatorScreen {
                            navController.navigate(ROUTE_HISTORY)
                        }
                    }
                    composable(ROUTE_HISTORY) {
                        HistoryScreen {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val ROUTE_CALCULATOR = "ROUTE_CALCULATOR"
        private const val ROUTE_HISTORY = "ROUTE_HISTORY"
    }
}