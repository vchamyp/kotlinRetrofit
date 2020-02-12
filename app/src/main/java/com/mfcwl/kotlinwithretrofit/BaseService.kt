package com.mfcwl.kotlinwithretrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class BaseService {
    public class TokenInterceptor() : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var initialRequest = chain.request()
                initialRequest = initialRequest.newBuilder()
                        .addHeader("token", "adb4d25d-7d61-43fd-83ad-395ca0df364b")
                        .build()
            return chain.proceed(initialRequest)
        }
    }

    companion object {
        protected const val BASE_URL = "https://newadmin.stagemfc.com/mfc_apis/v2/api/"
        protected var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        protected var tokenInterceptor = TokenInterceptor()
        protected var client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(tokenInterceptor)
                .addInterceptor(interceptor)
                .build()
        protected var gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
        public var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}