package com.devmuyiwa.themovflix.common.di

import com.devmuyiwa.themovflix.BuildConfig
import com.devmuyiwa.themovflix.common.data.remote.MovflixApi
import com.devmuyiwa.themovflix.common.data.remote.util.NetworkStatusInterceptor
import com.devmuyiwa.themovflix.common.data.remote.util.BASE_ENDPOINT
import com.devmuyiwa.themovflix.common.data.remote.util.KEY_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): MovflixApi =
        builder.build()
            .create(MovflixApi::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())

    @Provides
    fun provideOkHttpClient(
        requestInterceptor: Interceptor,
        networkStatusInterceptor: NetworkStatusInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(networkStatusInterceptor)
        .build()

    @Provides
    fun provideRequestInterceptor(): Interceptor =
        Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter(KEY_NAME, BuildConfig.API_KEY)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)
        }
}