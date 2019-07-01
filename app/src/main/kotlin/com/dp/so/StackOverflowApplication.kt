package com.dp.so

import android.app.Activity
import android.app.Application
import android.app.Service
import com.dp.so.common.AppComponent
import com.dp.so.common.AppConfigModule
import com.dp.so.common.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject


class StackOverflowApplication : Application(), HasActivityInjector, HasServiceInjector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildAppComponent()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appConfigModule(AppConfigModule(this))
            .build()

        appComponent.inject(this)
    }

    fun getAppComponent(): AppComponent {
        if (null == appComponent) {
            buildAppComponent()
        }
        return appComponent
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector
}
