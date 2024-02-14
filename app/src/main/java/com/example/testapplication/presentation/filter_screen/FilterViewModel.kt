package com.example.testapplication.presentation.filter_screen

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.model.FilterResponse
import com.example.testapplication.util.AssetUtil
import com.google.gson.Gson
import kotlinx.coroutines.launch

class FilterViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val _state = mutableStateOf(FilterState())
    val state: State<FilterState> = _state

    init {
        fetchDataFromJson()
    }

    private fun fetchDataFromJson() {
        viewModelScope.launch {
            val json = AssetUtil.readJsonFromAssets(getApplication(), "filers.json")
            val response = Gson().fromJson(json, FilterResponse::class.java)
            _state.value = FilterState(
                filterList = response.data
            )
        }
    }
}

