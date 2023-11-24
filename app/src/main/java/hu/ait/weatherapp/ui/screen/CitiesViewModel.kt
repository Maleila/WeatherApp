package hu.ait.weatherapp.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor() : ViewModel() {

    private val cityList = mutableStateListOf<String>()
    //double check how we did this w/o room/dao

    fun getAllCities(): List<String> {
        return cityList
    }

    fun addToList(newCity: String) {
        cityList.add(newCity)
    }

    fun removeItem(city: String) {
        cityList.remove(city) //can't remember the syntax for kotlin
    }

    fun clearAllCities() {
        cityList.clear()
    }


}