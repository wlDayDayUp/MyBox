package com.wl1217.mybox.http

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.blankj.utilcode.util.UriUtils
import com.blankj.utilcode.util.ZipUtils
import com.rxjava.rxlife.RxLife
import com.wl1217.library.format
import com.wl1217.library.log
import com.wl1217.library.toast
import com.wl1217.library.utils.DESCyptoUtil
import com.wl1217.mybox.BuildConfig
import com.wl1217.mybox.R
import com.wl1217.mybox.Url
import com.wl1217.mybox.bean.GetTestBean
import com.wl1217.mybox.bean.TestSignBean
import kotlinx.android.synthetic.main.activity_http.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import rxhttp.wrapper.entity.UpFile
import rxhttp.wrapper.param.RxHttp
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
import java.util.*

class HttpActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setTitle("动态权限申请")
                .setRationale("此功能需要相机权限，您拒绝了相机权限，请去允许此权限")
                .build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }


    companion object {
        const val CAMERA_PERMISSION_CODE = 123 /*相机权限的请求码*/
        const val REQUEST_IMAGE_CAPTURE = 111 /*相机回调码*/

        val NEED_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        getBt.setOnClickListener {


//            RxHttp.get(Url.getCs)
//                .add(
//                    hashMapOf(
//                        "username" to "wg",
//                        "age" to "29"
//                    )
//                )
//                .asObject(GetTestBean::class.java)
//                .`as`(RxLife.asOnMain(this))
//                .subscribe({
//                    it.toString().log()
//                    resultTv.text = it.toString()
//                }, {
//                    it.printStackTrace()
//                })
        }

        postBt.setOnClickListener {
            RxHttp.postForm(Url.testSign)
                .add(
                    Url.doTestSign(
                        "",
                        DESCyptoUtil.encode(Url.prm_contents_key, "q123456789"),
                        "0",
                        ""
                    )
                )
                .asObject(TestSignBean::class.java)
                .`as`(RxLife.asOnMain(this))
                .subscribe({
                    resultTv.text = it.toString()
                }, {
                    it.printStackTrace()
                })
        }

        cameraBt.setOnClickListener {
            takeCamera()
        }

        tpylBt.setOnClickListener {
            val photoURI = FileProvider.getUriForFile(
                this,
                "${BuildConfig.APPLICATION_ID}.fileprovider",
                File(currentPhotoPath)
            )
        }
    }

    var currentPhotoPath: String = ""

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = Date().format("yyyyMMdd_HHmmss")
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @AfterPermissionGranted(CAMERA_PERMISSION_CODE)
    fun takeCamera() {
        if (EasyPermissions.hasPermissions(this, *NEED_PERMISSION)) {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {

                    val photoFile =
                        try {
                            createImageFile()
                        } catch (ex: IOException) {
                            null
                        }

                    photoFile?.also {
                        val photoURI = FileProvider.getUriForFile(
                            this,
                            "${BuildConfig.APPLICATION_ID}.fileprovider",
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }

        } else {
            EasyPermissions.requestPermissions(
                this, "此功能需要获取相机的权限，请允许。", CAMERA_PERMISSION_CODE,
                *NEED_PERMISSION
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
                " 从开启权限的页面转跳回来 ".toast(this)
                "相机权限：${EasyPermissions.hasPermissions(this, *NEED_PERMISSION)}".toast(this)
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) { // 相机
                testIv.setImageURI(Uri.fromFile(File(currentPhotoPath)))

                setPic()

//                galleryAddPic()
//                val imageBitmap: Bitmap? = data?.extras?.get("data") as Bitmap
//                imageBitmap?.also {
//                    testIv.setImageBitmap(imageBitmap)
//                }
            }
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            this.sendBroadcast(mediaScanIntent)
        }
    }

    private fun setPic() {

        val b1 = BitmapFactory.decodeFile(currentPhotoPath)
        RxHttp.postForm(Url.oneUploadFile)
            .addFile("test_png", currentPhotoPath)
            .asString()
            .`as`(RxLife.asOnMain(this))
            .subscribe({
                resultTv.text = it
            }, { it.printStackTrace() })

        // Get the dimensions of the View
        val targetW: Int = imageView.width
        val targetH: Int = imageView.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}
