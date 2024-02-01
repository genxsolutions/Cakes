package com.genxsol.cakesapp.di.module

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.utilities.NetworkHelper
import com.example.utilities.NetworkHelperImpl
import com.genxsol.cakesapp.common.Const
import com.genxsol.cakesapp.common.dispatcher.DefaultDispatcherProvider
import com.genxsol.cakesapp.common.dispatcher.DispatcherProvider
import com.genxsol.cakesapp.common.logger.AppLogger
import com.genxsol.cakesapp.common.logger.Logger
import com.genxsol.cakesapp.data.model.CakesItem
import com.genxsol.cakesapp.data.network.ApiInterface
import com.genxsol.cakesapp.di.BaseUrl
import com.genxsol.cakesapp.ui.paging.CakesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Const.BASE_URL

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonFactory: GsonConverterFactory,
    ): ApiInterface {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun providePager(
        cakesPagingSource: CakesPagingSource
    ): Pager<Int, CakesItem> {
        return Pager(
            config = PagingConfig(
                Const.DEFAULT_QUERY_PAGE_SIZE
            )
        ) {
            cakesPagingSource
        }
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkHelperImpl(context)
    }

}