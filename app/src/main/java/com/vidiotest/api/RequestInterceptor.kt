package com.vidiotest.api

import android.content.Context
import android.util.Log
import com.vidiotest.utils.AppUtils
import dmax.dialog.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

class RequestInterceptor(val mContext: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .build()
        val response = chain.proceed(newRequest)
        var contentType: MediaType? = null
        var bodyString: String? = null
        if (response.body != null) {
            contentType = response.body!!.contentType()
            bodyString = response.body!!.string()
        }

        if(BuildConfig.DEBUG) {
            Log.w(newRequest.method, newRequest.url.toString())
            Log.w("${newRequest.method}-headers", newRequest.headers.toString())
            if(newRequest.method == "POST") {
                Log.w("${newRequest.method}-request", stringifyRequestBody(newRequest))
            }
            Log.w("${newRequest.method}-responseCode", response.code.toString())
            bodyString?.run { Log.w("${newRequest.method}-response", this) }
        }

        return if (response.body != null) {
            if(bodyString!!.contains("\"data\":false")) {
                bodyString = bodyString.replace("\"data\":false", "\"data\":{}")
            } else if(bodyString.contains("\"data\":true")) {
                bodyString = bodyString.replace("\"data\":true", "\"data\":{}")
            }

            if(bodyString.contains("\"delivery_address\":\"\"")) {
                bodyString = bodyString.replace("\"delivery_address\":\"\"", "\"delivery_address\":{}")
            }

            if(bodyString.contains("\"delivery_progress\":\"\"")) {
                bodyString = bodyString.replace("\"delivery_progress\":\"\"", "\"delivery_progress\":[]")
            }

            if(bodyString.contains("\"linked_facebook\":\"\"")) {
                bodyString = bodyString.replace("\"linked_facebook\":\"\"", "\"linked_facebook\":{}")
            }

            if(bodyString.contains("\"linked_instagram\":\"\"")) {
                bodyString = bodyString.replace("\"linked_instagram\":\"\"", "\"linked_instagram\":{}")
            }

            val body = bodyString.toResponseBody(contentType)
            response.newBuilder().body(body).build()
        } else {
            response
        }
    }

    private fun stringifyRequestBody(request: Request): String {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body!!.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}