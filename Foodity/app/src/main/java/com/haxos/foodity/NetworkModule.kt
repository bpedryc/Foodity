package com.haxos.foodity

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

/*@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthServer

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ResourceServer*/

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    /*@ResourceServer*/
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

    /*@AuthServer
    @Provides
    @Singleton
    fun provideAuthRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8180/auth/realms/FoodityKeycloak/protocol/openid-connect/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClient.Builder().build())
                .build()
    }*/


}