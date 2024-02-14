package com.example.testapplication.presentation.filter_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.data.model.DataItem
import com.example.testapplication.data.model.TaxonomiesItem

@Composable
fun FilterScreen(
    viewModel: FilterViewModel
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "back_arrow"
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "Filters",
                style = MaterialTheme.typography.titleLarge
            )
            /* Card(
                 shape = RoundedCornerShape(20.dp),
                 modifier = Modifier
                     .background(Color.White)
                     .padding(20.dp)
             ) {
                 Column {

                 }
             }*/
        }
        CollapsableLazyColumn(
            sections = state.filterList
        )

    }
}

@Composable
fun CollapsableLazyColumn(
    sections: List<DataItem>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                HeaderItem(dataItem = dataItem, isCollapsed = collapsed, onClick = {
                    collapsedState[i] = it
                })
            }
            if (!collapsed) {
                itemsIndexed(dataItem.taxonomies) { index, item ->
                    ExpandedItem(item) { onSelectedId ->

                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(
    dataItem: DataItem,
    isCollapsed: Boolean,
    onClick: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clickable {
                onClick(!isCollapsed)
            }
    ) {
        Text(
            dataItem.name!!,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .weight(1f)
        )
        Icon(
            Icons.Default.run {
                if (isCollapsed)
                    KeyboardArrowDown
                else
                    KeyboardArrowUp
            },
            contentDescription = "",
            tint = Color.LightGray,
        )
    }
}

@Composable
fun ExpandedItem(
    taxonomiesItem: TaxonomiesItem,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = taxonomiesItem.isSelected,
                onClick = { }
            )
    ) {
        RadioButton(
            selected = (taxonomiesItem.isSelected),
            onClick = {
                onOptionSelected(taxonomiesItem.id!!)
            }
        )
        Text(
            text = taxonomiesItem.name ?: "None",
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
