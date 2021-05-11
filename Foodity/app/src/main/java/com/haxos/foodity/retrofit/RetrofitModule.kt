package com.haxos.foodity.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    fun provideGson(): Gson {
    return GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()
    }

    @Provides
    @Singleton
    fun provideResourceRetrofit(gsonConfig: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8080/")
                .addConverterFactory(GsonConverterFactory.create(gsonConfig))
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

    @Provides
    fun provideNoteLogService(retrofit: Retrofit) : INoteLogService =
        retrofit.create(INoteLogService::class.java)

    @Provides
    fun provideProfileLogService(retrofit: Retrofit) : IProfileLogService =
        retrofit.create(IProfileLogService::class.java)
}