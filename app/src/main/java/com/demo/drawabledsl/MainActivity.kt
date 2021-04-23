package com.demo.drawabledsl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.forjrking.drawable.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DrawableBuilder.app = this.application

        linear.dividerDrawable = shapeDrawable {
            shape(Shape.RECTANGLE)
            solid("#84232323")
            size(-2f, 1f)
        }

        iv1.background = shapeDrawable {
            shape(Shape.RECTANGLE)
            solid("#ABE2E3")
            stroke(android.R.color.white, 2f, 5f, 8f)
        }

//        iv2 src shapeDrawable {
//            shape(Shape.OVAL)
//            solid(android.R.color.transparent)
//            stroke(android.R.color.black, 12f)
//            size(200f, 200f)
//        }

        iv2.src = shapeDrawable {
            shape(Shape.OVAL)
            solid(android.R.color.transparent)
            stroke(android.R.color.black, 12f)
            size(200f, 200f,isDp = false)
        }

//     点击效果等
        iv3 src selectorDrawable {
            normal = shapeDrawable {
                corner(20f)
                gradient(90, R.color.F97794, R.color.C623AA2)
            }
            pressed = shapeDrawable {
                corner(20f)
                solid("#84232323")
            }
        }

        //添加padding
        iv4 src insetDrawable {
            drawable = shapeDrawable {
                corner(10f)
                solid("#0396FF")
            }
            setInset(10)
        }
        iv3.setOnClickListener {
            Thread {
                saveBitmap(
                    BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                    File(filesDir, "img.png").absolutePath
                )
            }.start()
        }

        iv5 src resourceDrawable(R.mipmap.ic_launcher_round)

        iv6 src layerDrawable {
            addLayer(shapeDrawable {
                solid("#ffff00")
            })
            addLayer(resourceDrawable(R.mipmap.ic_launcher))
        }

    }

    private fun saveBitmap(bitmap: Bitmap, path: String?) {
        try {
            val filePic = File(path)
            if (!filePic.exists()) {
                filePic.parentFile.mkdirs()
                filePic.createNewFile()
            }
            val fos = FileOutputStream(filePic)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
            Log.i("TAG", "saveBitmap: ")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}