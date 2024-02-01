package com.genxsol.cakesapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.utilities.NetworkHelper
import com.genxsol.cakesapp.common.NoInternetException
import com.genxsol.cakesapp.common.dispatcher.DispatcherProvider
import com.genxsol.cakesapp.common.logger.Logger
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.repository.CakesRepository
import com.genxsol.cakesapp.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CakesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val cakesRepository: CakesRepository,
    private val pager: Pager<Int, CakesItem>,
    private val logger: Logger,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val _cakesStateItem = MutableStateFlow<UIState<List<CakesItem>>>(UIState.Empty)
    val cakesStateItem: StateFlow<UIState<List<CakesItem>>> = _cakesStateItem

    private val _cakesStateItemPaging = MutableStateFlow<PagingData<CakesItem>>(PagingData.empty())
    val cakesStateItemPaging: StateFlow<PagingData<CakesItem>> = _cakesStateItemPaging

    init {
        fetchCakes()
    }

    fun fetchCakes() {
        fetchCakesData()
    //TODO enable paging for large data set in lazy loading
    //fetchCakesWithPaging()
    }

    private fun fetchCakesWithPaging() {
        viewModelScope.launch {
            pager.flow.cachedIn(viewModelScope)
                .collect {
                    _cakesStateItemPaging.value = it
                }
        }
    }

    private fun fetchCakesData() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _cakesStateItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _cakesStateItem.emit(UIState.Loading)
            cakesRepository.getCakes().mapFilterCollect()
        }
    }
    private suspend fun Flow<List<CakesItem>>.mapFilterCollect() {
            this.flowOn(dispatcherProvider.io)
            .catch {
                _cakesStateItem.emit(UIState.Failure(it))
                logger.d("CakesViewModel", "Error")
            }
            .collect {
                _cakesStateItem.emit(UIState.Success(it))
                logger.d("CakesViewModel", "Success")
            }
    }

}