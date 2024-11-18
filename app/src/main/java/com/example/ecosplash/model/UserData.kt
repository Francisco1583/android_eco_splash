package com.example.ecosplash.model

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "coins")

class UserData(application: Application) : AndroidViewModel(application) {

    private val currentTankKey = intPreferencesKey("currentTank")
    private val currentSkinKey = intPreferencesKey("currentSkin")
    private val tutorialKey = booleanPreferencesKey("tutorial")
    private val clockActivationKey = intPreferencesKey("clockActivation")

    private val _currentTank = MutableLiveData<Int>()
    private val _currentSkin = MutableLiveData<Int>()
    private val _tutorial = MutableLiveData<Boolean>()
    private val _clockActivation = MutableLiveData<Int>()

    val currentTank: LiveData<Int> get() = _currentTank
    val currentSkin: LiveData<Int> get() = _currentSkin
    val tutorial: LiveData<Boolean> get() = _tutorial
    val clockAction: LiveData<Int> get() = _clockActivation

    init {
        loadCurrentTank()
        loadCurrentSkin()
        loadTutorial()
        loadClockActivation()
    }

    private fun loadClockActivation() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedClockActivation = preferences[clockActivationKey] ?: 0
            _clockActivation.value = storedClockActivation
        }
    }

    private fun loadTutorial() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedTutorial = preferences[tutorialKey] ?: true
            _tutorial.value = storedTutorial
        }
    }
    private fun loadCurrentTank() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedCurrentTank = preferences[currentTankKey] ?: 0
            _currentTank.value = storedCurrentTank
        }
    }

    private fun loadCurrentSkin() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedCurrentSkin = preferences[currentSkinKey] ?: 0
            _currentSkin.value = storedCurrentSkin
        }
    }

    private fun saveCurrentTank(newVal: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[currentTankKey] = newVal
            }
        }
    }

    private fun saveTutorial(newVal: Boolean) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[tutorialKey] = newVal
            }
        }
    }
    private fun saveClockAction(newVal: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[clockActivationKey] = newVal
            }
        }
    }

    private fun saveCurrentSkin(newVal: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[currentSkinKey] = newVal
            }
        }
    }

    fun setClockAction(value: Int) {
        _clockActivation.value = value
        saveClockAction(value)
    }
    fun setTutorial() {
        _tutorial.value = false
        saveTutorial(false)
    }

    fun setCurrentImage(index: Int, switchMode: Int) {

        if (switchMode == 1) {
            _currentSkin.value = index
            saveCurrentSkin(index)
        } else {
            _currentTank.value = index
            saveCurrentTank(index)
        }

    }

}