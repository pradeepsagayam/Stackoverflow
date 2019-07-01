package com.dp.so.common

import android.content.Context
import android.content.SharedPreferences
import com.dp.network.NetworkLibrary
import com.dp.so.StackOverflowApplication
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppConfigModule constructor(private val application: StackOverflowApplication) {

    @Provides
    fun providesApplication(): StackOverflowApplication = application

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun providesNetworkLibrary(networkConfigurationImpl: NetworkConfigurationImpl): NetworkLibrary {
        return NetworkLibrary(networkConfigurationImpl)
    }

    @Provides
    fun providesNetworkConfiguration(context: Context) = NetworkConfigurationImpl(context)

    @Provides
    fun providesRetrofit(
        networkLibrary: NetworkLibrary
    ): Retrofit {
        val okHttpBuilder = networkLibrary.okHttpClient()
            .newBuilder()

        val okHttpClient = okHttpBuilder
            .build()
        return networkLibrary.retrofit().newBuilder().client(okHttpClient).build()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun providesSettingsPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("stack_overflow_preference", Context.MODE_PRIVATE)
    }
}