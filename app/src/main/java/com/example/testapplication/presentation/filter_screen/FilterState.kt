package com.example.testapplication.presentation.filter_screen

import com.example.testapplication.data.model.DataItem

data class FilterState(
    val filterList: List<DataItem> = emptyList()
)