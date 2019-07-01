package com.dp.so.ui.main

class MainContract {

    interface View {

        fun showText(text: String)
    }

    interface Presenter {

        fun setView(view: View)

        fun onViewCreated()
    }
}