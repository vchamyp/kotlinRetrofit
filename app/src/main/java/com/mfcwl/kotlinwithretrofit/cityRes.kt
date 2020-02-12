package com.mfcwl.kotlinwithretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class cityRes {
    @SerializedName("citycode")
    @Expose
    var citycode: String? = null

    @SerializedName("cityname")
    @Expose
    var cityname: String? = null
}