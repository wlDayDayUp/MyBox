package com.wl1217.mybox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rxjava.rxlife.RxLife
import com.wl1217.mybox.http.HttpActivity
import kotlinx.android.synthetic.main.activity_main.*
import rxhttp.wrapper.param.RxHttp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        httpBt.setOnClickListener {
            startActivity(Intent(MainActivity@ this, HttpActivity::class.java))
        }
    }
}
