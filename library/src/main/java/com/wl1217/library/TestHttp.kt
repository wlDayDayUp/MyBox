package com.wl1217.library

import androidx.appcompat.app.AppCompatActivity
import com.rxjava.rxlife.RxLife
import rxhttp.wrapper.param.RxHttp

object TestHttp {
    fun testGet(activity: AppCompatActivity) {
        RxHttp.get("http://192.168.0.166:3000/test/getCs")
            .add(
                hashMapOf(
                    "username" to "wg",
                    "age" to "29"
                )
            )
            .asString()
            .`as`(RxLife.asOnMain(activity))
            .subscribe({
                it.toString().log()
            }, {
                it.printStackTrace()
            })
    }
}