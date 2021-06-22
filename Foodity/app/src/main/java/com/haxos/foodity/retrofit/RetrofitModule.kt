package com.haxos.foodity.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import com.haxos.foodity.ui.main.notes.content.IFileService
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
        val adapterFactory = RuntimeTypeAdapterFactory.of(NoteElement::class.java, "@type")
            .registerSubtype(TextNoteElement::class.java)
            .registerSubtype(ListNoteElement::class.java)
            .registerSubtype(ImageNoteElement::class.java)

        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .registerTypeAdapterFactory(adapterFactory)
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

    @Provides
    fun provideNotesCategoriesService(retrofit: Retrofit) : INotesCategoriesService =
        retrofit.create(INotesCategoriesService::class.java)

    @Provides
    fun provideNoteElementsService(retrofit: Retrofit) : INoteElementService =
        retrofit.create(INoteElementService::class.java)

    @Provides
    fun provideFileService(retrofit: Retrofit) : IFileService =
        retrofit.create(IFileService::class.java)
}