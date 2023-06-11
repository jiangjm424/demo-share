package com.happy.ishare.ui

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class DisableScrollLinearLayoutManager constructor(
    context: Context?, spanCount: Int
) : GridLayoutManager(context, spanCount) {
    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return false
    }
}
