package me.raghu.mvpassignment.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.raghu.mvpassignment.FeedActivity

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FeedActivityModule::class))
    internal abstract fun bindFeedActivity(): FeedActivity


}