package com.keremturker.coronavirusapi.util

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.keremturker.coronavirusapi.R

fun String?.emptyItemCheck(context: Context) = if (this.isNullOrEmpty()) {
    context.getString(R.string.none)
} else {
    this
}

fun SwipeRefreshLayout.onRefresh(function: () -> Unit) {

    setOnRefreshListener {
        isRefreshing = false
        function.invoke()
    }
}
