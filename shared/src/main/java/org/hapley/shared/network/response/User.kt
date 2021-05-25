package org.hapley.shared.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "about")
    val about: String,
    @Json(name = "created")
    val created: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "karma")
    val karma: Int,
    @Json(name = "submitted")
    val submitted: List<Int>
)