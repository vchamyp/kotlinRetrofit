package com.mfcwl.kotlinwithretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateReq {
    @SerializedName("param")
    @Expose
    var param: String? = null

}