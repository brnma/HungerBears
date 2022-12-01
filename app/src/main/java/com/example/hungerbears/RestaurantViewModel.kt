package com.example.hungerbears

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RestaurantViewModel(application: Application): AndroidViewModel(application) {

    private var disposable: Disposable? = null
    private val repository: RestaurantItemRepository = RestaurantItemRepository(RestaurantRoomDatabase.getDatabase(application).restaurantDao())

    fun refreshRestaurants(page: Int){

        disposable =
            RetrofitService.create("https://maps.googleapis.com/maps/api").getRestaurants("AIzaSyASOVUElVvEKOaRxZpUWhX8pDySvM3n8DE",page).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe()
//                {result -> showResult(result)},
//                {error -> showError(error)})
    }


}