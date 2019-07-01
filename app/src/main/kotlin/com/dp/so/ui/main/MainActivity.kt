package com.dp.so.ui.main

import android.os.Bundle
import com.dp.so.R
import com.dp.so.StackOverflowApplication
import com.dp.so.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun getToolbarTitle(): Int = R.string.app_name

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as StackOverflowApplication).getAppComponent().inject(this)

        presenter.setView(this)
        presenter.onViewCreated()
    }

    override fun showText(text: String) {
        mainScreenText.text = text
    }
}
