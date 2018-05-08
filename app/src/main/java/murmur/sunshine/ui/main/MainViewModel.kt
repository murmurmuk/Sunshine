package murmur.sunshine.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.util.toLiveData
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val repository: WeatherRepository) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val forecast: LiveData<List<WeatherEntry>> by lazy {
        repository.needFetchedNew()
                .subscribeOn(Schedulers.io())
                .flatMapCompletable {
                    if (it) {
                        Log.d("kanna", "fetch form net ${Thread.currentThread()}")
                        repository.fetchFromNet()
                    } else {
                        Log.d("kanna", "do nothing ${Thread.currentThread()}")
                        Completable.complete()
                    }
                }.subscribeBy(
                        onError = {
                            Log.d("kanna", "error occur ${it.message}")
                        },
                        onComplete = {
                            Log.d("kanna", "check finish")
                        }

                ).addTo(compositeDisposable)

        repository.fetchFromDb().toLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}