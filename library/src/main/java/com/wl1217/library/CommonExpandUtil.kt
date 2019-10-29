package com.wl1217.library

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.wl1217.library.utils.MessageDigestUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 *  显示吐司
 */
fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

/**
 *  日志打印（DEBUG模式下）
 */
fun Any.log(tag: String = "wl") {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this.toString())
    }
}

/**
 *  格式化日期
 */
fun Date.format(pattern: String = "yyyy-MM-dd HH:mm:ss"): String =
    SimpleDateFormat(pattern, Locale.CHINA).format(this)

/**
 *  MD5 加密
 */
fun String.md5() = MessageDigestUtil.md5(this)

/**
 *  sha1 加密
 */
fun String.sha1() = MessageDigestUtil.sha1(this)

/**
 *  sha256 加密
 */
fun String.sha256() = MessageDigestUtil.sha256(this)