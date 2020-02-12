package com.mfcwl.kotlinwithretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class StateRes {

    @SerializedName("statecode")
    @Expose
    var statecode: String? = null
    @SerializedName("statename")
    @Expose
    var statename: String? = null
}