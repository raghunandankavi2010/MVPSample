package me.raghu.mvpassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class CoroutineContextProvider: CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    open val uiScope: CoroutineScope by lazy {  CoroutineScope(coroutineContext) }
    open val POOL: CoroutineContext by lazy { Dispatchers.Default }
}