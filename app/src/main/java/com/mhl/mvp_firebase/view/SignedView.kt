package com.mhl.mvp_firebase.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface SignedView : MvpView {
    fun recyclerFill(data : ArrayList<Any>)
    fun dataIsEmpty()
    fun signOut()
}