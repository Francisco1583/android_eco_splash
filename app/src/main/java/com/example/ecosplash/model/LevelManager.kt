package com.example.ecosplash.model

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.abs

class LevelManager(application: Application): AndroidViewModel(application) {

    private val levelKey = intPreferencesKey("level")
    private val currentExperienceKey = intPreferencesKey("currentExperience")
    private val experienceToNextLevelKey = intPreferencesKey("experienceToNextLevel")

    private val _level = MutableLiveData<Int>()
    private val _currentExperience = MutableLiveData<Int>()
    private val _experienceToNextLevel = MutableLiveData<Int>()

    val level: LiveData<Int> get() = _level
    val currentExperience: LiveData<Int> get() = _currentExperience
    val experienceToNextLevel: LiveData<Int> get() = _experienceToNextLevel

    init {
        loadLevel()
        loadCurrentExperience()
        loadExperienceToNextLevel()
    }

    private fun loadLevel() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedLevel = preferences[levelKey] ?: 0
            _level.value = storedLevel
        }
    }
    private fun loadCurrentExperience() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedCurrentExperience = preferences[currentExperienceKey] ?: 0
            _currentExperience.value = storedCurrentExperience
        }
    }
    private fun loadExperienceToNextLevel() {
        viewModelScope.launch {
            val preferences = getApplication<Application>().dataStore.data.first()
            val storedExperienceToNextLevel = preferences[experienceToNextLevelKey] ?: 400
            _experienceToNextLevel.value = storedExperienceToNextLevel
        }
    }

    private fun saveLevel(value: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[levelKey] = value
            }
        }
    }
    private fun saveCurrentExperience(value: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[currentExperienceKey] = value
            }
        }
    }
    private fun saveExperienceToNextLevel(value: Int) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[experienceToNextLevelKey] = value
            }
        }
    }

    private fun levelUp() {
        val newLevel = (_level.value ?: 0) + 1
        _level.value = newLevel
        increaseExperienceToNextLevel()
        resetCurrentExperience()
        saveLevel(newLevel)
    }

    fun addExperience(value: Int) {
        val newExperience = (_currentExperience.value ?: 0) + value
        _currentExperience.value = newExperience
        saveLevel(newExperience)
        if ((_currentExperience.value ?: 0) > (_experienceToNextLevel.value ?: 0)) {
            levelUp()
        }
    }

    private fun increaseExperienceToNextLevel() {
        val newExperienceToNextLevel = (_experienceToNextLevel.value ?: 0) * 1.2
        _experienceToNextLevel.value = newExperienceToNextLevel.toInt()
        saveExperienceToNextLevel(newExperienceToNextLevel.toInt())
    }

    private fun resetCurrentExperience() {
        val newCurrentExperience = abs(
            (((_currentExperience.value ?: 0) - _experienceToNextLevel.value!!) ?: 0) ?: 0
        )
        _currentExperience.value = newCurrentExperience
        saveCurrentExperience(newCurrentExperience)
    }

}