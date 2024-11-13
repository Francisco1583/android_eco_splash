package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InventoryManager(application: Application) : AndroidViewModel(application) {

    private val tanksKey = stringPreferencesKey("tanks")
    private val skinsKey = stringPreferencesKey("skins")
    private val _tanks = MutableLiveData<BooleanArray>()
    private val _skins = MutableLiveData<BooleanArray>()
    val tanks: LiveData<BooleanArray> get() = _tanks
    val skins: LiveData<BooleanArray> get() = _skins

    init {
        loadTanks()
        loadSkins()
    }

    private fun loadTanks() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val tanksString = preferences[tanksKey] ?: ""

            val tanksArray = if (tanksString.isNotEmpty()) {
                tanksString.split(",").map { it.toInt() == 1 }.toBooleanArray()
            } else {
                BooleanArray(5) { false } // Valor por defecto si está vacío
            }

            _tanks.value = tanksArray
        }
    }

    private fun loadSkins() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val skinsString = preferences[skinsKey] ?: ""

            val skinsArray = if (skinsString.isNotEmpty()) {
                skinsString.split(",").map { it.toInt() == 1 }.toBooleanArray()
            } else {
                BooleanArray(5) { false }
            }

            _skins.value = skinsArray
        }
    }

    private fun saveTanks(newVal: BooleanArray) {
        viewModelScope.launch {
            val tanksList = newVal.map { if (it) 1 else 0 }

            getApplication<Application>().dataStore.edit { preferences ->
                preferences[tanksKey] = tanksList.joinToString(",")
            }
        }
    }

    private fun saveSkins(newVal: BooleanArray) {
        viewModelScope.launch {
            val skinsList = newVal.map { if (it) 1 else 0 }

            getApplication<Application>().dataStore.edit { preferences ->
                preferences[skinsKey] = skinsList.joinToString(",")
            }
        }
    }

    fun unlockItem(index: Int, switchMode: Int) {

        if (switchMode == 1) {
            _tanks.value?.let { tanksArray ->
                if (index in tanksArray.indices) {
                    tanksArray[index] = true
                    _tanks.value = tanksArray
                    saveTanks(tanksArray)
                }
            }
        } else {
            _skins.value?.let { skinsArray ->
                if (index in skinsArray.indices) {
                    skinsArray[index] = true
                    _skins.value = skinsArray
                    saveSkins(skinsArray)
                }
            }
        }
    }


    fun getItemObtained(index: Int, switchMode: Int): Boolean {

        return if (switchMode == 1) {
            _tanks.value?.get(index) ?: false
        } else {
            _skins.value?.get(index) ?: false
        }

    }

}
