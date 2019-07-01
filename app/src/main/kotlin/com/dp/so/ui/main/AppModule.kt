package com.dp.so.ui.main

import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun providesMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter
}
