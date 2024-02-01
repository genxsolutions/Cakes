package com.genxsol.cakesapp.data.repository

import com.genxsol.cakesapp.common.Const.DEFAULT_PAGE_NUM
import com.genxsol.cakesapp.common.util.removeDuplicates
import com.genxsol.cakesapp.common.util.sortCakesByName
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CakesRepository @Inject constructor(
    private val network: ApiInterface
) {

    suspend fun getCakes(pageNumber: Int = DEFAULT_PAGE_NUM): Flow<List<CakesItem>> {
        return flow {
            val cakes = network.getCakes()
            emit(
                processCakes(cakes)
            )
        }.catch {
            throw Exception("Network error")
        }
    }
}

private fun processCakes(cakes: List<CakesItem>): List<CakesItem> {
    // Remove duplicates and sort cakes by name
    return cakes.removeDuplicates().sortCakesByName()
}