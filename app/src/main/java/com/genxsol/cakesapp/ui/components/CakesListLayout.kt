package com.genxsol.cakesapp.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.genxsol.cakesapp.data.model.CakesItem


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CakesLayout(
    cakesList: List<CakesItem>,
    cakeClicked: (String) -> Unit
) {
    LazyColumn {
        items(cakesList.size) { index ->
            AnimatedCakeItem(cakesList[index]){
                cakeClicked(it)
            }
            Divider(thickness = 1.dp)
        }
    }
}