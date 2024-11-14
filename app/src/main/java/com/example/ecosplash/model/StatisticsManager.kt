package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StatisticsManager(application: Application): AndroidViewModel(application) {

    private val totalShowersKey = intPreferencesKey("quickShowers")
    private val quickShowersKey = intPreferencesKey("quickShowers")
    private val litersSavedKey = floatPreferencesKey("litersSaved")

    private val _totalShowers = MutableLiveData<Int>()
    private val _quickShowers = MutableLiveData<Int>()
    private val _litersSaved = MutableLiveData<Float>()

    val totalShowers: LiveData<Int> get() = _totalShowers
    val quickShowers: LiveData<Int> get() = _quickShowers
    val litersSaved: LiveData<Float> get() = _litersSaved

    init {
        loadTotalShowers()
        loadQuickShowers()
        loadLitersSaved()
    }

    private fun loadTotalShowers() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedTotalShowers = preferences[totalShowersKey] ?: 0
            _totalShowers.value = storedTotalShowers
        }
    }

    private fun loadQuickShowers() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedQuickShowers = preferences[quickShowersKey] ?: 0
            _quickShowers.value = storedQuickShowers
        }
    }

    private fun loadLitersSaved() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedLitersSaved = preferences[litersSavedKey] ?: 0.0f
            _litersSaved.value = storedLitersSaved
        }
    }

    private fun saveTotalShowers(value: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[totalShowersKey] = value
            }
        }
    }

    private fun saveQuickShowers(value: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[quickShowersKey] = value
            }
        }
    }

    private fun saveLitersSaved(value: Float) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[litersSavedKey] = value
            }
        }
    }

    fun addShowers() {
        val newAmount = (_totalShowers.value ?: 0) + 1
        _totalShowers.value = newAmount
        saveTotalShowers(newAmount)
    }
    fun addQuickShowers() {
        val newAmount = (_quickShowers.value ?: 0) + 1
        _quickShowers.value = newAmount
        saveQuickShowers(newAmount)
    }
    fun addLitersSaved(amount: Float) {
        val newAmount = (_litersSaved.value ?: 0.0f) + amount
        _litersSaved.value = newAmount
        saveLitersSaved(newAmount)
    }

    fun resetQuickShowers() {
        val newAmount = 0
        _quickShowers.value = newAmount
        saveQuickShowers(newAmount)
    }

}