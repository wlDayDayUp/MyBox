package com.wl1217.mybox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wl1217.library.log
import com.wl1217.library.toast
import com.wl1217.mybox.bean.User
import com.wl1217.mybox.db.MyDbHelper
import com.wl1217.mybox.db.UserDao
import com.wl1217.mybox.http.HttpActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpBt.setOnClickListener {
            startActivity(Intent(MainActivity@ this, HttpActivity::class.java))
        }


        val dbHelper = MyDbHelper(this)

        addBt.setOnClickListener {
            val db = dbHelper.readableDatabase
            val userDao = UserDao(db)
            try {
//            val name = nameEt.text.toString()
//            val age = ageEt.text.toString()
//            val addUser = userDao.addUser(
//                User(name, age.toInt())
//            )
//            db.close()
//            if (addUser) {
//                "添加成功".toast(this)
//            } else {
//                "添加失败".toast(this)
//            }
                db.beginTransaction()
                for (i in 1..100) {
                    userDao.addUser(
                        User(
                            "name$i",
                            i
                        )
                    )
                }
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
                db.close()
            }
        }

        queryAllBt.setOnClickListener {
            val db = dbHelper.readableDatabase
            val userDao = UserDao(db)
            val sb = StringBuffer()
            userDao.queryAll().forEach {
                sb.append(it.name + " " + it.age + "\n")
            }
            db.close()
            resultTv.text = sb.toString()
        }
    }
}
