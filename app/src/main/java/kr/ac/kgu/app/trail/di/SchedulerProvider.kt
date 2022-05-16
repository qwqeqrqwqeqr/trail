package kr.ac.kgu.app.trail.di

import io.reactivex.Scheduler


interface SchedulerProvider {
    val uiScheduler: Scheduler
    val ioScheduler: Scheduler
    val subScheduler: Scheduler
}