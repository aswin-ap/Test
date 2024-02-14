package com.example.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class FilterResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class LocationsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("Guid")
	val guid: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class TaxonomiesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("Guid")
	val guid: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("locations")
	val locations: List<LocationsItem?>? = null,

	var isSelected: Boolean = false
)

data class DataItem(

	@field:SerializedName("taxonomies")
	val taxonomies: List<TaxonomiesItem>,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	var expandable : Boolean = false
)
