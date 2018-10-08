package me.raghu.mvpassignment.network

import android.util.Log

import java.util.concurrent.atomic.AtomicBoolean

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleSource
import io.reactivex.annotations.NonNull

/**
 * Created by raghu on 13/5/17.
 */

class SingleCache<T>(private val source: Single<T>) : SingleSource<T> {

    private val refresh = AtomicBoolean(true)
    @Volatile
    private var current: Single<T>? = null


    init {
        this.current = source
    }

    fun reset() {
        refresh.set(true)
    }


    override fun subscribe(@NonNull observer: SingleObserver<in T>) {
        if (refresh.compareAndSet(true, false)) {
            current = source.cache()
            Log.d("Cache", "source")
        }
        current!!.subscribe(observer)
    }

}