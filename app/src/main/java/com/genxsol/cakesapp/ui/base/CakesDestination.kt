package com.genxsol.cakesapp.ui.base
sealed class Screen(val route: String) {
    object ListScreen : Screen("list")
    object PopupScreen : Screen("popup")
}