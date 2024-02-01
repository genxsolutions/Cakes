package com.genxsol.cakesapp.ui.paging

import androidx.paging.PagingSource
import com.example.utilities.NetworkHelper
import com.genxsol.cakesapp.common.dispatcher.DispatcherProvider
import com.genxsol.cakesapp.common.dispatcher.TestDispatcherProvider
import com.genxsol.cakesapp.common.networkhelper.TestNetworkHelper
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.repository.CakesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doThrow
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CakesPagingSourceTest {

    @Mock
    private lateinit var cakesRepository: CakesRepository

    private lateinit var pagingSource: CakesPagingSource
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var networkHelper: NetworkHelper

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        networkHelper = TestNetworkHelper()
        pagingSource = CakesPagingSource(cakesRepository, dispatcherProvider)
    }


    @Test
    fun load_whenResponseFailed_shouldGiveError() {
        runTest {
            // Given
            val page = 1
            val error = RuntimeException("Fake error")
            doThrow(error)
                .`when`(cakesRepository)
                .getCakes(page)

            // When
            val result = pagingSource.load(PagingSource.LoadParams.Refresh(page, 1, false))

            // Then
            val expected = PagingSource.LoadResult.Error<Int, CakesItem>(error)
            assertEquals(expected.toString(), result.toString())
        }
    }

}