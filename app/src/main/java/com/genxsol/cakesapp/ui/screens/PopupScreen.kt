package com.genxsol.cakesapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

@Composable
fun PopupScreen(navController: NavController, text: String) {
    Dialog(
        onDismissRequest = { navController.popBackStack() }
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .width(200.dp)
                .height(100.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

