package com.happy.ishare.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.happy.ishare.core.Scene

data class SceneEntry(val scene: Scene, @DrawableRes val icon: Int, @StringRes val title: Int)
