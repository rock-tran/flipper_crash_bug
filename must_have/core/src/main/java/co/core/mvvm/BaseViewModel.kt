package co.core.mvvm

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import co.core.livedata.SingleLiveEvent
import co.core.toolbox.SchedulersFacade
import co.core.toolbox.extensions.subscribeForAll
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel(protected val schedulersFacade: SchedulersFacade, application: Application) : AndroidViewModel(application), IViewModel {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    val loadingLV = MutableLiveData<Status>()

    val errorLV = MutableLiveData<Throwable>()

    val messageLV = SingleLiveEvent<String>()

    fun clearTasks() {
        disposables.clear()
    }

    override fun onCleared() {
        disposables.clear()
    }

    fun context(): Context {
        return getApplication<Application>().applicationContext!!
    }

    override fun <T> configThread(single: Single<T>): Single<T> {
        return single.subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun showMessage(message: String?) {
        messageLV.postValue(message)
    }

    fun showMessage(@StringRes mesRes: Int) {
        messageLV.postValue(context().getString(mesRes))
    }

    fun <T> shortCallAPI(single: Single<T>,
                         onSuccess: ((T) -> Unit)? = null) {
        configSubscribeAndLoad(single, onSuccess)
    }

    fun <T> configSubscribeAndLoad(single: Single<T>,
                                   onSuccess: ((T) -> Unit)? = null) {
        addDisposable(single.subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribeForAll(onSuccess = {
                    loadingLV.postValue(Status.SUCCESS)
                    onSuccess?.invoke(it)
                }, onLoading = {
                    loadingLV.postValue(Status.LOADING)
                }, onError = {
                    loadingLV.postValue(Status.ERROR)
                    errorLV.postValue(it)
                }))
    }
}