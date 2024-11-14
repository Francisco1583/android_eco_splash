package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AchievementsManager(application: Application): AndroidViewModel(application) {
    private val coinsKey = intPreferencesKey("coins")
    private val _coins = MutableLiveData<Int>()
    val coins: LiveData<Int> get() = _coins
}