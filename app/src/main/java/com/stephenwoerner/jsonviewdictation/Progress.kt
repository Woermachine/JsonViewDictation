package com.stephenwoerner.jsonviewdictation

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates a progress view for the announcement flow
 */
class Progress {
    @SerializedName(value = "show_progress")
    var showProgress : Boolean? = null

    @SerializedName(value = "progress_type")
    var progressType : ProgressType? = null

    @SerializedName(value = "primary_color")
    var primaryColor : String? = null

    @SerializedName(value = "secondary_color")
    var secondaryColor : String? = null
}
