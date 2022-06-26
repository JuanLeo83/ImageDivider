package model

import com.google.gson.annotations.SerializedName

data class Animation(
    @SerializedName("name") val name: String,
    @SerializedName("row") val row: Int,
    @SerializedName("frames") val frames: Int
)