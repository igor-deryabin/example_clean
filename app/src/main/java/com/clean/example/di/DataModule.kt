package com.clean.example.di

import com.clean.example.BuildConfig
import com.clean.example.data.remote.Api
import com.clean.example.data.repositories.PreferencesRepository
import com.clean.example.data.repositories.PreferencesRepositoryImpl
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ObsoleteCoroutinesApi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ObsoleteCoroutinesApi
val dataModule = module {

    single<Api> {
        val gson = GsonBuilder()
            .create()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = object : Interceptor {
            private val preferences: PreferencesRepository = get()
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request().newBuilder()
//                    .addHeader("Authorization", "Bearer ${preferences.getToken().token}")
                    .build()
                return chain.proceed(request)
            }
        }

        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        val client: OkHttpClient = clientBuilder
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.clean.example.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(Api::class.java)
    }

    single<PreferencesRepository> { PreferencesRepositoryImpl(androidApplication()) }
}