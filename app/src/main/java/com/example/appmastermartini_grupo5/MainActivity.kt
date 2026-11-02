package com.example.appmastermartini_grupo5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appmastermartini_grupo5.ui.screens.AppNavigation
import com.example.appmastermartini_grupo5.ui.theme.AppMasterMartini_Grupo5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMasterMartini_Grupo5Theme {
                AppNavigation()
            }
        }
    }
}