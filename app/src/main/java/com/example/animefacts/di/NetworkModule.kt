package com.example.animefacts.di

import android.content.Context
import com.example.animefacts.data.JikanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.jikan.moe/v4/"
    val cacheSize = 10L * 1024 * 1024 // 10 MB

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideCache(
        @ApplicationContext context: Context
    ): Cache{
        return Cache(
            directory = File(context.cacheDir, "http_cache"),
            maxSize = cacheSize
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient{
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor {chain->
                val request = chain.request()

                val newRequest = request.newBuilder()
                    .cacheControl(
                        CacheControl.Builder()
                            .maxAge(1, TimeUnit.MINUTES)
                            .build()
                    )
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideJikanApi(retrofit: Retrofit): JikanApi{
        return retrofit.create(JikanApi::class.java)
    }
}