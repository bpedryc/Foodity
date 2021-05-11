package com.haxos.foodity.app

import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.UserSession
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class SessionModule {

    @Binds
    abstract fun bindCurrentUserInfo(userSession: UserSession) : ICurrentUserInfo
}