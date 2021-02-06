package com.haxos.foodity

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit) : UserService =
            retrofit.create(UserService::class.java)
}