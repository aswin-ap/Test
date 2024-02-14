package com.example.testapplication.presentation.filter_screen

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.model.FilterResponse
import com.example.testapplication.data.model.TaxonomiesItem
import com.example.testapplication.util.AssetUtil
import com.google.gson.Gson
import kotlinx.coroutines.launch

class FilterViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val _state = mutableStateOf(FilterState())
    val state: State<FilterState> = _state

    private val _taxList = mutableStateOf(listOf<TaxonomiesItem>())
    val taxList: State<List<TaxonomiesItem>> = _taxList


    init {
        fetchDataFromJson()
    }

    private fun fetchDataFromJson() {
        viewModelScope.launch {
            val json = AssetUtil.readJsonFromAssets(getApplication(), "filers.json")
            val response = Gson().fromJson(json, FilterResponse::class.java)
            val list = mutableListOf<TaxonomiesItem>()
            _state.value = FilterState(
                filterList = response.data
            )
            response.data.forEach {
                list.addAll(it.taxonomies)
            }
            _taxList.value = list
        }
    }

    fun updateSelection(id: Int) {
        viewModelScope.launch {
            val item = _taxList.value.find { it.id == id }
            item?.let {

            }
        }
    }
}

