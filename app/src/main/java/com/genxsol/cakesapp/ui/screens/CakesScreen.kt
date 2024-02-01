package com.genxsol.cakesapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.genxsol.cakesapp.R
import com.genxsol.cakesapp.common.NoInternetException
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.ui.base.ShowError
import com.genxsol.cakesapp.ui.base.ShowLoading
import com.genxsol.cakesapp.ui.base.UIState
import com.genxsol.cakesapp.ui.components.CakeItem
import com.genxsol.cakesapp.ui.components.CakesLayout
import com.genxsol.cakesapp.ui.viewmodels.CakesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CakesScreen(
    cakesViewModel: CakesViewModel = hiltViewModel(),
    cakeClicked: (String) -> Unit
) {
    val cakesUiState: UIState<List<CakesItem>> by cakesViewModel.cakesStateItem.collectAsStateWithLifecycle()

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            cakesViewModel.fetchCakes()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (cakesUiState) {
            is UIState.Loading -> {
                if (!isRefreshing)
                    ShowLoading()
            }

            is UIState.Failure -> {
                isRefreshing = false
                var errorText = stringResource(id = R.string.something_went_wrong)
                if ((cakesUiState as UIState.Failure<List<CakesItem>>).throwable is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    cakesViewModel.fetchCakes()
                }
            }

            is UIState.Success -> {
                isRefreshing = false
                if ((cakesUiState as UIState.Success<List<CakesItem>>).data
                        .isEmpty()
                ) {
                    ShowError(text = stringResource(R.string.no_data_available))
                } else {
                    CakesLayout(cakesList = (cakesUiState as UIState.Success<List<CakesItem>>).data) {
                        cakeClicked(it)
                    }
                }
            }

            is UIState.Empty -> {

            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CakesScreenPaging(
    cakesViewModel: CakesViewModel = hiltViewModel(),
    cakeClicked: (String) -> Unit
) {
    val pagingResponse = cakesViewModel.cakesStateItemPaging.collectAsLazyPagingItems()

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            cakesViewModel.fetchCakes()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (pagingResponse.loadState.refresh) {
            is LoadState.Loading -> {
                if (!isRefreshing)
                    ShowLoading()
            }

            is LoadState.Error -> {
                isRefreshing = false
                var errorText = stringResource(id = R.string.something_went_wrong)
                if ((pagingResponse.loadState.refresh as LoadState.Error).error is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    cakesViewModel.fetchCakes()
                }
            }

            else -> {
                isRefreshing = false
                CakesPagingAppend(pagingResponse, cakeClicked)
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}

@Composable
private fun CakesPagingAppend(
    pagingResponse: LazyPagingItems<CakesItem>,
    cakeClicked: (String) -> Unit,
) {
    LazyColumn {
        items(pagingResponse.itemCount) {
            if (pagingResponse[it] != null) {
                CakeItem(pagingResponse[it]!!) { description ->
                    cakeClicked(description)
                }
            }
        }
        pagingResponse.apply {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                strokeWidth = 1.dp
                            )
                        }
                    }
                }

                is LoadState.Error -> {
                    item {
                        ShowError(
                            text = stringResource(id = R.string.retry_on_pagination),
                            retryEnabled = true
                        ) {
                            pagingResponse.retry()
                        }
                    }
                }

                else -> {}
            }
        }
    }
}
