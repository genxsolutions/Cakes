package com.genxsol.cakesapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import app.cash.turbine.test
import com.example.utilities.NetworkHelper
import com.genxsol.cakesapp.common.dispatcher.DispatcherProvider
import com.genxsol.cakesapp.common.dispatcher.TestDispatcherProvider
import com.genxsol.cakesapp.common.logger.Logger
import com.genxsol.cakesapp.common.logger.TestLogger
import com.genxsol.cakesapp.common.networkhelper.TestNetworkHelper
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.repository.CakesRepository
import com.genxsol.cakesapp.ui.base.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CakesViewModelTest {

    @Mock
    private lateinit var cakesRepository: CakesRepository

    @Mock
    private lateinit var cakesPagingSource: Pager<Int, CakesItem>

    private lateinit var logger: Logger
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var networkHelper: NetworkHelper

    @Before
    fun setUp() {
        logger = TestLogger()
        dispatcherProvider = TestDispatcherProvider()
        networkHelper = TestNetworkHelper()
        Dispatchers.setMain(dispatcherProvider.main)
    }

    @Test
    fun fetchCakes_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<CakesItem>()))
                .`when`(cakesRepository)
                .getCakes()
            val savedStateHandle = SavedStateHandle()

            val viewModel = CakesViewModel(
                savedStateHandle,
                cakesRepository,
                cakesPagingSource,
                logger,
                dispatcherProvider,
                networkHelper
            )
            viewModel.cakesStateItem.test {
                assertEquals(UIState.Success(emptyList<CakesItem>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(cakesRepository, Mockito.times(1)).getCakes()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}