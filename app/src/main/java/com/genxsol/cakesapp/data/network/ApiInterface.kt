package com.genxsol.cakesapp.data.network

import com.genxsol.cakesapp.common.Const.DEFAULT_KEY1
import com.genxsol.cakesapp.common.Const.DEFAULT_KEY2
import com.genxsol.cakesapp.data.model.Cakes
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("t-reed/{key1}/raw/{key2}/waracle_cake-android-client")
    suspend fun getCakes(
        @Path("key1") key1: String = DEFAULT_KEY1,
        @Path("key2") key2: String = DEFAULT_KEY2,
    ): Cakes
}
