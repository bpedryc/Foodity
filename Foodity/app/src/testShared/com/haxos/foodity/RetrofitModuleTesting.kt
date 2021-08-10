package com.haxos.foodity

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import com.haxos.foodity.retrofit.*
import com.haxos.foodity.ui.main.notes.content.IFileService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
)
object RetrofitModuleTesting {

    @Provides
    fun provideClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        val adapterFactory = RuntimeTypeAdapterFactory
            .of(NoteElement::class.java, "@type")
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
    fun provideResourceRetrofit(gsonConfig: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(gsonConfig))
                .client(client)
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