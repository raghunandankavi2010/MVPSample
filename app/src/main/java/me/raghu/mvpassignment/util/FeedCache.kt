package me.raghu.mvpassignment.util

import android.util.LruCache

class FeedCache constructor() {
    val lru: LruCache<Any, Any> = LruCache(1024)

}