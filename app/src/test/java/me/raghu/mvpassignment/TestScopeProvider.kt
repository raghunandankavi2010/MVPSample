package me.raghu.mvpassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


class TestScopeProvider : CoroutineContextProvider() {
    private val job = Job()

    override val uiScope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)
    override val POOL: CoroutineScope = CoroutineScope(EmptyCoroutineContext)


}