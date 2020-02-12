package com.mfcwl.kotlinwithretrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response


class MainActivity : AppCompatActivity(), HttpCallResponse {

    var TAG = javaClass.simpleName;
    var global =GlobalText()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var apicallone: TextView = findViewById(R.id.apicallone);
        apicallone.setOnClickListener {
            getDurationListone()
        }
        var apicalltwo: TextView = findViewById(R.id.apicalltwo);
        apicalltwo.setOnClickListener {
            getDurationListtwo()
        }

        var apicallthree: TextView = findViewById(R.id.apicallthree);
        apicallthree.setOnClickListener {
            getDurationListthree()
        }

        var apicallfour: TextView = findViewById(R.id.apicallfour);
        apicallfour.setOnClickListener {
            getDurationListfour()
        }

    }

    private fun getDurationListone() {
        val dash = WebCall()
        dash.getCall(global.GETFRIDAYLIST, this)
    }

    private fun getDurationListtwo() {
        val dash = WebCall()
        dash.getCall(global.GETZONELIST, this)
    }

    private fun getDurationListthree() {
        val dash = WebCall()

        var req =StateReq()
        req.param=""

        dash.postCall(global.GETSTATELIST,req , this)
    }

    private fun getDurationListfour() {
        val dash = WebCall()

        var req =StateReq()
        req.param="64"

        dash.postCall(global.GETCITYLIST, req,this)
    }

    override fun OnSuccess(obj: Any?, requestCode: String) {

        when (requestCode) {
            global.GETFRIDAYLIST -> {
                val mRes = obj as Response<List<Any?>?>
                val mData = mRes.body()!!
                Log.i("TAG", "onResponse:1 " + obj.body())
            }global.GETZONELIST -> {
                val mRes = obj as Response<List<Any?>?>
                val mData = mRes.body()!!
                Log.i("TAG", "onResponse:2 " + obj.body())
            }global.GETSTATELIST -> {
                val mRes = obj as Response<List<Any?>?>
                Log.i("TAG", "onResponse:3 " + obj.body())
                Log.i("TAG", "onResponse:3 " + mRes.body())

            var stateStr = Gson().toJson(mRes.body())
            val stateModel: List<StateRes?> = Gson().fromJson(stateStr, object : TypeToken<ArrayList<StateRes?>?>() {}.type)

            for (i in 0 until stateModel.size) {

                Log.i(TAG,"stateCode=${stateModel.get(i)?.statecode}")
                Log.i(TAG,"stateName=${stateModel.get(i)?.statename}")
            }

            }global.GETCITYLIST -> {

            val mRes = obj as Response<List<Any?>?>

            var cityStr = Gson().toJson(mRes.body())
            val cityModel: List<cityRes?> = Gson().fromJson(cityStr, object : TypeToken<ArrayList<cityRes?>?>() {}.type)

            for (i in 0 until cityModel.size) {

                Log.i(TAG,"stateCode=${cityModel.get(i)?.citycode}")
                Log.i(TAG,"stateName=${cityModel.get(i)?.cityname}")
            }
                Log.i("TAG", "onResponse:4 " + obj.body())
            }
            else -> {
                Log.i(TAG, "Else")
            }
        }
    }

      override fun OnFailure(mThrowable: Throwable?) {

    }
}