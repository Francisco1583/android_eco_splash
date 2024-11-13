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

class StrikeManager(application: Application) : AndroidViewModel(application) {
    private val strikesKey = intPreferencesKey("strikes")
    private val _strikes = MutableLiveData<Int>()

    val strikes: LiveData<Int> get() = _strikes

    init {
        loadStrikes()
    }

    private fun loadStrikes() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedStrikes = preferences[strikesKey] ?: 0
            _strikes.value = storedStrikes
        }
    }

    private fun saveStrikes(amount: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[strikesKey] = amount
            }
        }
    }

    fun addStrikes() {
        val newAmount = (_strikes.value ?: 0) + 1
        _strikes.value = newAmount
        saveStrikes(newAmount)
    }

    fun resetStrikes() {
        val newAmount = 0
        _strikes.value = newAmount
        saveStrikes(newAmount)
    }

}