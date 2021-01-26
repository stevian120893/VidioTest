package com.vidiotest.utils

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View
{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val defaultInterval: Int = 1000
    var lastTimeClicked: Long = 0
    setOnClickListener {
        if(SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return@setOnClickListener
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(it)
    }
}

fun ShimmerFrameLayout.show() {
    this.startShimmer()
    this.visibility = android.view.View.VISIBLE
}

fun ShimmerFrameLayout.hide() {
    this.stopShimmer()
    this.visibility = android.view.View.GONE
}

fun convertDate(date: String): String? {
    val sdf = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
    var testDate: Date? = null
    try {
        testDate = sdf.parse(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm a")
    val newFormat = formatter.format(testDate)

    return newFormat
}