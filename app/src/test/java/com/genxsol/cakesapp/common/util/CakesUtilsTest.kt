package com.genxsol.cakesapp.common.util

import com.genxsol.cakesapp.data.model.CakesItem
import org.junit.Assert.assertEquals
import org.junit.Test

class CakesUtilsTest {

    @Test
    fun `removeDuplicates removes duplicate cakes`() {
        // Arrange
        val cakes = listOf(
            CakesItem(title = "Lemon cheesecake", desc = "A cheesecake made of lemon", image ="url1"),
            CakesItem(title = "Lemon cheesecake", desc = "A cheesecake made of lemon", image = "url1"),
            CakesItem(title = "Victoria sponge", desc = "Sponge with jam", image = "url2")
        )

        // Act
        val uniqueCakes = cakes.removeDuplicates()

        // Assert
        assertEquals(2, uniqueCakes.size)
        assertEquals("Lemon cheesecake", uniqueCakes[0].title)
        assertEquals("Victoria sponge", uniqueCakes[1].title)
    }

    @Test
    fun `sortCakesByName sorts cakes by name`() {
        // Arrange
        val cakes = listOf(
            CakesItem(title = "Victoria sponge", desc = "Sponge with jam", image = "url1"),
            CakesItem(title = "Lemon cheesecake", desc = "A cheesecake made of lemon", image ="url2")
        )

        // Act
        val sortedCakes = cakes.sortCakesByName()

        // Assert
        assertEquals("Lemon cheesecake", sortedCakes[0].title)
        assertEquals("Victoria sponge", sortedCakes[1].title)
    }
}
