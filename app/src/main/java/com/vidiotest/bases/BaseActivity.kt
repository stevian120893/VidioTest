package com.vidiotest.bases

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import dmax.dialog.SpotsDialog

open class BaseActivity: AppCompatActivity(), MvpView {

    lateinit var mLoadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLoadingDialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(true)
            .build()
    }

    open fun initListener() {
//        ibBack.setSafeOnClickListener {
//            onBackPressed()
//        }
    }

    override fun showLoading() {
        mLoadingDialog.show()
    }

    override fun dismissLoading() {
        mLoadingDialog.dismiss()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action != MotionEvent.ACTION_DOWN) {
            if (currentFocus != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}