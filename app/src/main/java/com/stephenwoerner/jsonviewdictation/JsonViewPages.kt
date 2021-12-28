package com.stephenwoerner.jsonviewdictation

import com.google.gson.annotations.SerializedName

/**
 * Root data object passed to AnnouncementFragment,
 *
 * Encapsulates the set of information to needed to create an AnnouncementFragment
 */
class JsonViewPages {
    val progress: Progress? = null

    @SerializedName(value = "show_buttons")
    val showButtons: Boolean? = null

    @SerializedName(value = "page_array")
    val pageArray : Array<Page>? = null

//    @SerializedName(value = "button_style")
//    val buttonStyle : ButtonStyle? = null

    @SerializedName(value = "button_color")
    val buttonColor : String? = null

    @SerializedName(value = "button_tint")
    val buttonTint : String? = null
}
