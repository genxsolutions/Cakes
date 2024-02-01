package com.genxsol.cakesapp.data.repository


import com.genxsol.cakesapp.data.model.Cakes
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.network.ApiInterface
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CakesRepositoryTest {

    @Mock
    private lateinit var apiInterface: ApiInterface

    private lateinit var cakesRepository: CakesRepository

    @Before
    fun setUp() {
        cakesRepository = CakesRepository(apiInterface)
    }

    @Test
    fun getCakes_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runBlocking {
            // Arrange
            val cakeItem = CakesItem(
                title = "title",
                desc = "description",
                image = "url"
            )

            val cakeItems = listOf(cakeItem)
            val cakes = Cakes().apply { addAll(cakeItems) }
            `when`(apiInterface.getCakes()).thenReturn(cakes)

            // Act
            val actual = cakesRepository.getCakes().first()

            // Assert
            assertEquals(cakeItems, actual)
        }
    }

    @Test(expected = Exception::class)
    fun `getCakes throws error on network failure`(): Unit = runBlocking {
        val mockNetwork: ApiInterface = mock()

        // Mock network error response
        `when`(mockNetwork.getCakes()).thenThrow(Exception("Network error"))

        // Call the repository method
        val repository = CakesRepository(mockNetwork)
        repository.getCakes()
    }

}