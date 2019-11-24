# MyBox

> 这是一个神奇的盒子，我的学习Android用的，哈哈😄

提交测试

## 三方库

- RxHttp
  **网络请求[Go to GitHub](https://github.com/liujingxing/RxHttp)** Apache License 2.0

- utilcodex **Android 工具类
  [Go to GitHub](https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md)** Apache License 2.0

- easypermissions **动态权限请求
  [Go to GitHub](https://github.com/googlesamples/easypermissions)** Apache License 2.0
    
- BaseRecyclerViewAdapterHelper **RecyclerView适配器
  [Go to GitHub](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)**
        
- AgentWeb **AgentWeb
  [Go to GitHub](https://github.com/Justson/AgentWeb)** Apache License 2.0
          
- Matisse **Matisse
  [Go to GitHub](https://github.com/zhihu/Matisse)** Apache License
  2.0
   
- Glide **Glide [Go to GitHub](https://github.com/bumptech/glide)**
  Apache License 2.0
  
- SmartRefreshLayout **SmartRefreshLayout
  [Go to GitHub](https://github.com/scwang90/SmartRefreshLayout)** Apache License 2.0
  
  
    
## 使用

- [MyApp](https://github.com/wlDayDayUp/MyBox/blob/master/app/src/main/java/com/wl1217/mybox/MyApp.java)
  初始化网络请求、utilcodex工具类

- [Url](https://github.com/wlDayDayUp/MyBox/blob/master/app/src/main/java/com/wl1217/mybox/Url.kt) 需要Rebuild项目，生产RxHttp类

- manifest <application> [res/xml](https://github.com/wlDayDayUp/MyBox/tree/master/app/src/main/res/xml)

  1.  android:name=".MyApp"
    
  2.  android:networkSecurityConfig="@xml/network_config"
    
  3.   Android 7.0 适配
        ```
         <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="包名.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
        ```

- 项目中需要有一个bean的包名，存放bean，避免混淆

- [混淆文件](https://github.com/wlDayDayUp/MyBox/tree/master/app/proguard-rules.pro)

- [GlobConfig](https://github.com/wlDayDayUp/MyBox/tree/master/app/src/main/java/com/wl1217/mybox/GlobConfig.kt)
  全局配置类