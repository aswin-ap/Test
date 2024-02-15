package com.example.testapplication.presentation.filter_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.model.DataItem
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

    fun updateSelection(dataItem: DataItem, id: Int) {
        viewModelScope.launch {
            val item = _state.value.filterList.find { it == dataItem }
            item?.let {
                val updatedFilterList = _state.value.filterList.map { dataItem ->
                    Log.d("list", dataItem.expandable.toString())
                    val updatedTaxonomies = dataItem.taxonomies.map { taxItem ->
                        if (taxItem.id == id) {
                            taxItem.copy(isSelected = !taxItem.isSelected)
                        } else {
                            taxItem
                        }
                    }
                    dataItem.copy(
                        taxonomies = updatedTaxonomies,
                        count = dataItem.count + (if (updatedTaxonomies.any { it.isSelected }) 1 else 0)
                    )
                }
                _state.value = _state.value.copy(filterList = updatedFilterList)
            }
        }
    }
}

