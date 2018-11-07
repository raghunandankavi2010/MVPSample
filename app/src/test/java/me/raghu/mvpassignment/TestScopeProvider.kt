package me.raghu.mvpassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TestScopeProvider : CoroutineContextProvider() {

    @ExperimentalCoroutinesApi
    override val uiScope: CoroutineScope =  CoroutineScope(Unconfined)
    @ExperimentalCoroutinesApi
    override val POOL: CoroutineScope = CoroutineScope(Unconfined)

}