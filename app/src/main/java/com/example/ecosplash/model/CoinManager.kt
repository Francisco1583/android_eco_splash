package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CoinManager(application: Application) : AndroidViewModel(application) {

    private val coinsKey = intPreferencesKey("coins")
    private val _coins = MutableLiveData<Int>()
    val coins: LiveData<Int> get() = _coins

    init {
        loadCoins()
    }

    private fun loadCoins() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedCoins = preferences[coinsKey] ?: 0
            _coins.value = storedCoins
        }
    }

    private fun saveCoins(amount: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[coinsKey] = amount
            }
        }
    }

    fun addCoins(amount: Int) {
        val newAmount = (_coins.value ?: 0) + amount
        _coins.value = newAmount
        saveCoins(newAmount)
    }

    fun subtractCoins(amount: Int) {
        val newAmount = (_coins.value ?: 0) - amount
        _coins.value = newAmount
        saveCoins(newAmount)
    }
}