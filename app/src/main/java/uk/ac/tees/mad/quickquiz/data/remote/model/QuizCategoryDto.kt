package uk.ac.tees.mad.quickquiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaCategoryDto(
    @SerialName("id")
    val id:Int,
    @SerialName("name")
    val name : String
)