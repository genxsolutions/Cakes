package com.genxsol.cakesapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.genxsol.cakesapp.R
import com.genxsol.cakesapp.data.model.CakesItem

@Composable
fun CakeItem(cake: CakesItem, onItemClick: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable {
            onItemClick(cake.desc)
        }) {
        Row(modifier = Modifier.height(120.dp)) {
            CakeImage(urlToImage = cake.image, title = cake.title)
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                CakeTitle(title = cake.title)
            }
        }
    }
}

@Composable
fun CakeImage(urlToImage: String, title: String?) {
    AsyncImage(
        model = urlToImage,
        error = painterResource(R.drawable.ic_cake),
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(150.dp)
    )
}

@Composable
fun CakeTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleMedium
    )
}