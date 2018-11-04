package me.raghu.mvpassignment

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineContextProvider: CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    open val uiScope: CoroutineScope by lazy {  CoroutineScope(coroutineContext) }
    open val POOL: CoroutineContext by lazy { Dispatchers.Default }
}