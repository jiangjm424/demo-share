package com.happy.ishare.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


fun Bitmap.toThumbnail(destW: Int, destH: Int): Bitmap {
    return Bitmap.createScaledBitmap(this, destW, destH, true)
}

fun Bitmap.toByteArray(needRecycle: Boolean = false): ByteArray {
    val output = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 80, output)
    if (needRecycle) {
        this.recycle()
    }
    val result = output.toByteArray()
    try {
        output.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}
