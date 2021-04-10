package com.haxos.foodity.retrofit

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
    fun provideResourceRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit) : IUserService =
            retrofit.create(IUserService::class.java)

    @Provides
    fun provideAuthService(retrofit: Retrofit) : IAuthService =
            retrofit.create(IAuthService::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit) : IProfileService =
            retrofit.create(IProfileService::class.java)

    @Provides
    fun provideNotesService(retrofit: Retrofit) : INotesService =
            retrofit.create(INotesService::class.java)
}