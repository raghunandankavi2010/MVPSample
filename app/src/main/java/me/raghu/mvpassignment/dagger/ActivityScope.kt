package me.raghu.mvpassignment.dagger

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

/**
 * Custom activity scope
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
annotation class ActivityScope
