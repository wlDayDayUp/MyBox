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


        var ifLogin = false /*登录状态*/

        /*网络请求签名用的key*/
        const val prm_private_key_sign =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJgA1FF0cceexRyBRET/s0Vv21SETpKJ12Q1AnX5bGu0ZpzMKazK2DHgmnw9/uRNGkDhi5qFd0EDrd2EhesdoM0YiBjcgF8QVMBrrwDfg/ymgVJHTbvfXc9z6VrxBNZ0tR7p66vjJvmeGIpdIj1xTDJPIUa5iZtIitf0aj7R/QcFAgMBAAECgYEAl+MkRayU0UK+dghZfpT/vx/Ry7dViCpC17f8mIQ/KbSfYIjGEAq1U8m6tPGCFQFSfYii5in9izKw9owpAVKezSgrX84M67Olf1e3/F3kP2mvcpt+9J1zvmwAIaGEnH749dStMhYKUXrSVYztYEbW/KWEVOlr66NtO2BlX4nPn/UCQQDJFPi2kmvP+jRmGvObuMmP5VKeDUa6a5x8ed9+lW4k2RcR/o4MRziK200K7Yc1zxOh3nGV56nXpYYoFGQ8Lo+vAkEAwYRtfE7CB11wlhxjRmEUeNDvgDcKOxMTYqVzJTJhnf6HpMBLf2CaN6llpqnzd/5v8U/hNTB4qW5FNUwxzVDtiwJAOujp9JLxAv1KXJ+IvxZj9sQ4cBVzoynjrpQF5g/hNOpk1+C7vN0gs42MBKeR9TG1jrackE5Oc98KbrOKqhb6AQJAQAB3YXR+0YbC1LLA1qcG6UpY27PYa19MuwRzR6sZA/MJk6CRl6gweRZKa0usHVSGW24K0tecIJU6yHzRv9DzLwJAY0vP9EPY/eFKZ2aY7y4i5nqthnr2hHLG+oqJuFZryv1dc091oYkHL+1a3Tb9bkjOGNEVFCAdayL3O9f6HNNy3Q=="

        /*数据加密*/
        const val prm_contents_key = "Yk_DES@^" // DESCyptoUtil.encode("Yk_DES@^", "q123456789")

        /** ------------------ 接口 start------------------ */
        const val getCs = "$baseUrl/test/getCs"  /*测试get请求*/

        const val testSign = ""

        fun doTestSign(username: String, userpwd: String, logintype: String, token: String) =
            RequestParamConvert.getRequestData(
                hashMapOf(
                    "username" to username,
                    "userpwd" to userpwd,
                    "logintype" to logintype
                ), token, prm_private_key_sign
            )

        const val oneUploadFile = "$baseUrl/test/uploadfile"

    }
}