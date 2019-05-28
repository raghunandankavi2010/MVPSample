package me.raghu.mvpassignment.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.raghu.mvpassignment.DemoApplication
import me.raghu.mvpassignment.FeedActivity
import me.raghu.mvpassignment.network.FetchFeed
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class,ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

    }

    fun inject(app: DemoApplication)


}
