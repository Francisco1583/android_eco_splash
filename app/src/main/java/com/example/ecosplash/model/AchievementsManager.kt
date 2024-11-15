package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AchievementsManager(application: Application): AndroidViewModel(application) {

    private val achievementsKey = stringPreferencesKey("tanks")

    private val _achievements = MutableLiveData<BooleanArray>()

    val achievements: LiveData<BooleanArray> get() = _achievements

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val achievementsString = preferences[achievementsKey] ?: ""

            val skinsArray = if (achievementsString.isNotEmpty()) {
                achievementsString.split(",").map { it.toInt() == 1 }.toBooleanArray()
            } else {
                BooleanArray(3) { false }
            }

            _achievements.value = skinsArray
        }
    }

    private fun saveAchievements(newVal: BooleanArray) {
        viewModelScope.launch {
            val achievementsList = newVal.map { if (it) 1 else 0 }

            getApplication<Application>().dataStore.edit { preferences ->
                preferences[achievementsKey] = achievementsList.joinToString(",")
            }
        }
    }

    fun unlockAchievement(index: Int) {
        _achievements.value?.let { achievementsArray ->
            if (index in achievementsArray.indices) {
                achievementsArray[index] = true
                _achievements.value = achievementsArray
                saveAchievements(achievementsArray)
            }
        }
    }

    fun isUnlocked(index: Int): Boolean{
        return _achievements.value?.get(index) ?: false
    }

}