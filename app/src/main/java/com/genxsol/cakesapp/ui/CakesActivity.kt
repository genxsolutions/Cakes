package com.genxsol.cakesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.genxsol.cakesapp.ui.base.CakeNavHost
import com.genxsol.cakesapp.ui.theme.CakeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CakesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CakeAppTheme {
                Surface {
                    CakeNavHost()
                }
            }
        }
    }

}