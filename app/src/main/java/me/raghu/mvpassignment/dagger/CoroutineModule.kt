package me.raghu.mvpassignment.dagger

import dagger.Module
import dagger.Provides
import me.raghu.mvpassignment.CoroutineContextProvider

@Module
class CoroutineModule {

    @Provides
    internal fun providerCoroutineProvider() : CoroutineContextProvider {
        return CoroutineContextProvider()
    }

}