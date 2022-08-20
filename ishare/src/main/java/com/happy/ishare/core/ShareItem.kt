package com.happy.ishare.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ShareItem(@DrawableRes val icon: Int,
                     @StringRes val text: Int,
                     val platform: Platform,
                     val scene: Scene)

interface OnShareItemClick {
    fun onShareItemClick(item: ShareItem)
}
