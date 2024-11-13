package com.example.ecosplash.model

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.integerResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecosplash.classes.Sombrero
import com.example.ecosplash.fisbowlanimated
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "coins")

class UserData(application: Application) : AndroidViewModel(application) {

    private val currentTankKey = intPreferencesKey("currentTank")
    private val currentSkinKey = intPreferencesKey("currentSkin")

    private val _currentTank = MutableLiveData<Int>()
    private val _currentSkin = MutableLiveData<Int>()

    val currentTank: LiveData<Int> get() = _currentTank
    val currentSkin: LiveData<Int> get() = _currentSkin

    init {
        loadCurrentTank()
        loadCurrentSkin()
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

    private fun saveCurrentSkin(newVal: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[currentSkinKey] = newVal
            }
        }
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

    fun substractCoins(amount: Int) {
        val newAmount = (_coins.value ?: 0) - amount
        _coins.value = newAmount
        saveCoins(newAmount)
    }
}

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

        if (switchMode == 1) {
            return _tanks.value?.get(index) ?: false
        }
        else {
            return _skins.value?.get(index) ?: false
        }

    }

}
