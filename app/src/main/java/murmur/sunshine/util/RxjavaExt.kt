package murmur.sunshine.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() =
        LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun Disposable.addTo(compositeDisposable: CompositeDisposable) =
        compositeDisposable.add(this)

