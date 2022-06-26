package model

import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("imagePath") val imagePath: String,
    @SerializedName("widthFrames") val widthFrames: Int,
    @SerializedName("heightFrames") val heightFrames: Int,
    @SerializedName("animations") val animations: List<Animation>,
    @SerializedName("outputFolder") val outputFolder: String
)