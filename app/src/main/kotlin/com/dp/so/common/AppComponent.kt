package com.dp.so.common

import com.dp.so.StackOverflowApplication
import com.dp.so.ui.main.AppModule
import com.dp.so.ui.main.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,

            AppConfigModule::class,

            AppModule::class
        ]
)
interface AppComponent {

    fun inject(stackOverflowApplication: StackOverflowApplication)

    fun inject(mainActivity: MainActivity)
}
