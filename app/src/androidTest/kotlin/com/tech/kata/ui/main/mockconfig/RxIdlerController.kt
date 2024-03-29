package com.dp.so.ui.main.mockconfig

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class RxIdlerController {

    private var idlingResources: Array<IdlingResource>? = null

    fun unregisterAll() {
        idlingResources = getIdlingResources()
        IdlingRegistry.getInstance().unregister(*idlingResources!!)
    }

    fun reRegisterAll() {
        IdlingRegistry.getInstance().register(*idlingResources!!)
    }

    private fun getIdlingResources(): Array<IdlingResource> {
        val resources = IdlingRegistry.getInstance().resources
        return resources.toTypedArray()
    }

    companion object {

        fun initialize() {
            RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2.x Io Scheduler"))
            Schedulers.io() //force initialization
            if (!isInitialized) {
                throw RuntimeException("Initialization failed")
            }
        }

        val isInitialized: Boolean
            get() = !IdlingRegistry.getInstance().resources.isEmpty()
    }

}
