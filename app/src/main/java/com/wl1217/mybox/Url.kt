package com.wl1217.mybox

import com.wl1217.library.http.RequestParamConvert
import rxhttp.wrapper.annotation.DefaultDomain

/**
 * 用于生成网络请求RxHttp类
 */
class Url {
    companion object {
        @DefaultDomain
        const val baseUrl = "http://192.168.0.166:3000"

        /** ------------------ 接口 start------------------ */
        const val getCs = "$baseUrl/test/getCs"  /*测试get请求*/

        const val testSign = ""

        fun doTestSign(username: String, userpwd: String, logintype: String, token: String) =
            RequestParamConvert.getRequestData(
                hashMapOf(
                    "username" to username,
                    "userpwd" to userpwd,
                    "logintype" to logintype
                ), token, GlobConfig.prm_private_key_sign
            )

        const val oneUploadFile = "$baseUrl/test/uploadfile"

    }
}