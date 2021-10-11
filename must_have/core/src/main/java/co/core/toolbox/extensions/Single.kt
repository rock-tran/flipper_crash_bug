package co.core.toolbox.extensions

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable


fun <T> Single<T>.subscribeForAll(onLoading: (() -> Unit)? = null,
                                  onSuccess: ((response: T) -> Unit)? = null,
                                  onError: ((throwable: Throwable) -> Unit)? = null): Disposable {
    return doOnSubscribe { onLoading?.invoke() }
            .subscribe({ onSuccess?.invoke(it) })
            { onError?.invoke(it) }
}

fun <T> Single<T>.subscribeNothing(): Disposable {
    return subscribe({}, {})
}