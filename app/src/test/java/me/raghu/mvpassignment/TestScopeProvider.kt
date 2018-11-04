package me.raghu.mvpassignment

import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

class TestScopeProvider : CoroutineContextProvider() {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext + job
    override val POOL: CoroutineContext = EmptyCoroutineContext
}