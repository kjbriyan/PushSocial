package com.kjbriyan.socialapps.ui.editcontent

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ResponseStatus
import com.kjbriyan.socialapps.ui.additem.AddPostPresenter
import com.kjbriyan.socialapps.util.Helper
import com.kjbriyan.socialapps.util.SharedPrefs
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.android.synthetic.main.activity_add_post.iv_barang
import kotlinx.android.synthetic.main.activity_add_post.pb_add
import kotlinx.android.synthetic.main.activity_edit.*
import java.io.*

class EditActivity : AppCompatActivity(),EditView {

    private val TAG = "UpdateFrag"
    val REQUEST_CODE_CAMERA = 10
    val REQUEST_CODE_GALLERY = 11
    var filegambar: File? = null
    var imgUri: Uri? = null
    val presenter = EditPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        checkPermissionsAndOpenFilePicker()
        actionclick()
        view()

    }

    private fun view(){
        val id_post = intent.extras?.getString("id").toString()
        val imge = intent.extras?.getString("img").toString()
        val ket = intent.extras?.getString("ket").toString()
        val judul = intent.extras?.getString("judul").toString()
        et_judul.setText(judul)
        et_ket.setText(ket)
    }

    private fun actionclick(){
        val id_post = intent.extras?.getString("id").toString()
        val iduser = Prefs.getString(SharedPrefs.idUser, "").toString()
        findViewById<Button>(R.id.btn_update)?.setOnClickListener {
            val bitmap: Bitmap
            iv_barang?.setDrawingCacheEnabled(true)
            bitmap = iv_barang!!.drawingCache
            val img = Helper().getEncoded64ImageStringFromBitmap(bitmap).toString()
            Log.d(TAG,"aa"+ iduser)
            presenter.updateItem( id_post,et_judul.text.toString(), et_ket?.text.toString(), img)
        }
        iv_barang.setOnClickListener {
            val dialogitem =
                arrayOf<CharSequence>("Camera", "Galery")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Pilih")
            builder.setItems(dialogitem, DialogInterface.OnClickListener { dialogInterface, i ->
                when (i) {
                    0 -> open_camera()
                    1 -> open_galey()
                }
            })
            builder.create().show()
        }
    }



    override fun onShowloading() {
        pb_add.visibility = View.VISIBLE
    }

    override fun onHideloading() {
        pb_add.visibility = View.INVISIBLE
    }

    override fun onDatasend(result: ResponseStatus?) {
        Toasty.success(this, result?.messages.toString(), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDataeror(t: Throwable) {
        Toasty.error(this, t.cause.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_GALLERY) {
                if (data != null) {
                    imgUri = data.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            contentResolver, imgUri
                        )
                        tmpFile(bitmap)
                        iv_barang.setImageBitmap(bitmap)
                        val img = Helper().getEncoded64ImageStringFromBitmap(bitmap)

                        Log.d(TAG, "uri bitmap " + bitmap.toString())
//
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                try {
                    val thumbnail = MediaStore.Images.Media.getBitmap(
                        contentResolver, imgUri
                    )
                    tmpFile(thumbnail)
                    iv_barang.setImageBitmap(thumbnail)

                    Log.d(TAG, "uri bitmap " + thumbnail.toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun tmpFile(bitmap: Bitmap): File? {
        filegambar = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis().toString() + "_iamge.jpeg"
        )
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val bitmadata: ByteArray = bos.toByteArray()
        try {
            val fos = FileOutputStream(filegambar)
            fos.write(bitmadata)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return filegambar
    }

    private fun checkPermissionsAndOpenFilePicker() {
        val permissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
//            Toast.makeText(this, "Disetujui", Toast.LENGTH_SHORT).show()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                showError()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                showError()
            }
        }
    }

    private fun open_galey() {
        val galery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(galery, REQUEST_CODE_GALLERY)
    }

    private fun open_camera() {
        var values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
        imgUri = this.contentResolver?.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 0
        private const val FILE_PICKER_REQUEST_CODE = 1

    }
}