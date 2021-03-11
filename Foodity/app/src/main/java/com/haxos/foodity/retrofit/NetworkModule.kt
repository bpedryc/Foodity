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
    fun provideUserService(retrofit: Retrofit) : UserService =
            retrofit.create(UserService::class.java)

    @Provides
    fun provideAuthService(retrofit: Retrofit) : AuthService =
            retrofit.create(AuthService::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit) : ProfileService =
            retrofit.create(ProfileService::class.java)

    @Provides
    fun provideNotesService(retrofit: Retrofit) : NotesService =
            retrofit.create(NotesService::class.java)
}