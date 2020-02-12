package com.mfcwl.kotlinwithretrofit

/**
 * Created by Surya on 11/06/18.
 */
interface HttpCallResponse {
    fun OnSuccess(obj: Any?, requestCode: String)
    fun OnFailure(mThrowable: Throwable?)
}