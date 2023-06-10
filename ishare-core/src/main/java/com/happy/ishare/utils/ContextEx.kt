package com.happy.ishare.utils

import android.content.Context
import android.content.pm.PackageManager

fun Context.getAppMetaData(key: String): String? {
    val data: String?
    val appInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    data = appInfo.applicationInfo.metaData?.getString(key)
    return data
}
