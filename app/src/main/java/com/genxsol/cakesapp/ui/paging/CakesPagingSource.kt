package com.genxsol.cakesapp.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.genxsol.cakesapp.common.dispatcher.DispatcherProvider
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.repository.CakesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CakesPagingSource @Inject constructor(
    private val cakesRepository: CakesRepository,
    private val dispatcherProvider: DispatcherProvider
) : PagingSource<Int, CakesItem>() {

    override fun getRefreshKey(state: PagingState<Int, CakesItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CakesItem> {
        val page = params.key ?: 1
        lateinit var loadResult: LoadResult<Int, CakesItem>

        loadResult = try {
            val articles = withContext(dispatcherProvider.io) {
                cakesRepository.getCakes(page).first()
            }
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (articles.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
        return loadResult
    }
}