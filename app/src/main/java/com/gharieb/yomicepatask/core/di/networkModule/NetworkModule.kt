package com.gharieb.yomicepatask.core.di.networkModule

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS
import com.gharieb.yomicepatask.data.remote.authentication.LoginApiService
import com.gharieb.yomicepatask.data.remote.main.PharmaciesApiService
import com.gharieb.yomicepatask.data.remote.main.ReturnRequestsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .addInterceptor(provideHeaderInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
    ): Retrofit = Retrofit.Builder()
        .baseUrl(CONSTANTS.BASE_URL)
        .addConverterFactory(provideConverterFactory())
        .client(provideHttpClient())
        .build()


    @Singleton
    @Provides
    fun provideAuthenticationApiService( retrofit: Retrofit): LoginApiService =
        retrofit.create(LoginApiService::class.java)

    @Singleton
    @Provides
    fun providePharmaciesApiService( retrofit: Retrofit): PharmaciesApiService =
        retrofit.create(PharmaciesApiService::class.java)

    @Singleton
    @Provides
    fun provideReturnRequestsApiService( retrofit: Retrofit): ReturnRequestsApiService =
        retrofit.create(ReturnRequestsApiService::class.java)



}