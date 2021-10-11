package co.core.mvvm

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable


interface IViewModel {

    fun <T> configThread(single: Single<T>): Single<T>

    fun addDisposable(disposable: Disposable)
}