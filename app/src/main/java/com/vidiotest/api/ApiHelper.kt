package com.vidiotest.api

import android.content.Context
import android.widget.Toast
import com.vidiotest.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val mContext: Context) {

    fun <T> callService(onRequest: (() -> Call<T>), apiListener: ApiListener<T>) {
        if (AppUtils.isNetworkAvailable(mContext)) {
            try {
                val call = onRequest()
                call.enqueue(object: Callback<T> {

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if(response.body() != null) {
                            val modelResponse = response.body()!!
                            if(response.code() == 200) {
                                apiListener.onSuccess(modelResponse)
                            } else {
                                apiListener.onFailed(response.message())
                            }
                        } else {
                            // ketika response body null
                            if(response.errorBody() != null) {
//                                val modelResponse: T = Gson().fromJson(response.errorBody()!!.string(),
//                                    object: TypeToken<T>(){}.type)
                                apiListener.onFailed(response.errorBody()!!.string())
                            }
                        }
                        /*} else {
                            // response code selain 200
                            Toast.makeText(mContext, "Under Maintenance", Toast.LENGTH_SHORT).show()
                        }*/
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        //apiListener.onFailed(t.localizedMessage)
                        Toast.makeText(mContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
                apiListener.onFailed(e.localizedMessage)
            }
        } else {
            //apiListener.onFailed(mContext.getResources().getString(R.string.no_connection))
        }
    }

}