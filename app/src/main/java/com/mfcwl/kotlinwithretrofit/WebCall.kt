package com.mfcwl.kotlinwithretrofit

import android.util.Log
import androidx.annotation.NonNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url


class WebCall : BaseService() {

    fun postCall(url: String, req: Any?,  mHttpCallResponse: HttpCallResponse) {

        val mInterFace = retrofit.create(ApiInterface::class.java)

        val mCall: Call<List<Any?>?> = mInterFace.postFromWeb(req,url)

        mCall.enqueue(object : Callback<List<Any?>?> {
            override fun onResponse(@NonNull call: Call<List<Any?>?>, @NonNull response: Response<List<Any?>?>) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onResponse: "+response.body());
                    mHttpCallResponse.OnSuccess(response, url)
                } else {
                    val t = Throwable()
                    mHttpCallResponse.OnFailure(t)
                }
            }
            override fun onFailure(call: Call<List<Any?>?>, t: Throwable) {
                mHttpCallResponse.OnFailure(t)
            }
        })

    }

    fun getCall(url: String, mHttpCallResponse: HttpCallResponse) {

        val mInterFace = retrofit.create(ApiInterface::class.java)

        val mCall: Call<List<Any?>?> = mInterFace.getFromWeb(url)

        mCall.enqueue(object : Callback<List<Any?>?> {
            override fun onResponse(@NonNull call: Call<List<Any?>?>, @NonNull response: Response<List<Any?>?>) {
                if (response.isSuccessful) {
                    mHttpCallResponse.OnSuccess(response, url)
                } else {
                    val t = Throwable()
                    mHttpCallResponse.OnFailure(t)
                }
            }
            override fun onFailure(call: Call<List<Any?>?>, t: Throwable) {
                mHttpCallResponse.OnFailure(t)
            }
        })
    }

    interface ApiInterface {

        @POST fun postFromWeb(@Body request: Any?, @Url url: String?): Call<List<Any?>?>
        @GET fun getFromWeb(@Url url: String?): Call<List<Any?>?>

    }
}


