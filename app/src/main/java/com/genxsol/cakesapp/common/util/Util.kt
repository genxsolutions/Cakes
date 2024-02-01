package com.genxsol.cakesapp.common.util

import com.genxsol.cakesapp.data.model.CakesItem

// Extension function to remove duplicate entries from the list of cakes
fun List<CakesItem>.removeDuplicates(): List<CakesItem> {
    return distinctBy { it.title }
}

// Extension function to sort the list of cakes by name
fun List<CakesItem>.sortCakesByName(): List<CakesItem> {
    return sortedBy { it.title }
}