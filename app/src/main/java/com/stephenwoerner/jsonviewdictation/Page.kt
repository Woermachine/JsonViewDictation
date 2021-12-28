package com.stephenwoerner.jsonviewdictation

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates a page in the announcement view.
 */
class Page {
    val text: String? = null

    @SerializedName("text_color")
    val textColor: String? = null
    val image: String? = null

    @SerializedName("background_color")
    val backgroundColor : String? = null

}
