package com.genxsol.cakesapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.genxsol.cakesapp.data.model.CakesItem

@ExperimentalAnimationApi
@Composable
fun AnimatedCakeItem(
    cake: CakesItem,
    cakeClicked: (String) -> Unit
) {
    val isVisible = remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = isVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .animateContentSize()
            .padding(vertical = 4.dp)
    ) {
        CakeItem(cake) { description ->
            cakeClicked(description)
        }
    }

    // Trigger animation when the item is added to the list
    androidx.compose.runtime.SideEffect {
        isVisible.value = true
    }
}