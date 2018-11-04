package me.raghu.mvpassignment

import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


class TestScopeProvider : CoroutineContextProvider() {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext + job
    override val POOL: CoroutineContext = EmptyCoroutineContext
}