package com.example.loklok_demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.AsyncTask
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class DrawCustomView : View {
    private var path: Path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var sizeBrush: Int = 0
    private lateinit var btmView : Bitmap
    private lateinit var serviceIntent : Intent
    private val myFile = File("data/data/com.elselse.loklok/cache/", "local.png")
    private val myFile1 = File("data/data/com.elselse.loklok/cache/", "notlocal.png")

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    init {
        context.startService(serviceIntent)
        sizeBrush = 6
        paint.apply {
            color = Color.BLACK
            isDither = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeWidth = toPx(sizeBrush)
        }
    }


    @SuppressLint("StaticFieldLeak")
    fun saveBitmapToStorage(bitmap: Bitmap) {
        object : AsyncTask<Void?, String?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                Log.d("Saving Initiated", "Yes! In progress!")
                var fos: FileOutputStream? = null
                try {
                    fos = FileOutputStream(myFile)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos!!)
                    fos = FileOutputStream(myFile1)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos!!)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    try {
                        fos!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                return null
            }

            override fun onPreExecute() {
                super.onPreExecute()
                Log.d("Saving Initiated", "Yes!")
            }

            override fun onPostExecute(unused: Void?) {
                super.onPostExecute(unused)
            }
        }.execute(null, null, null)
    }

    fun toPx(sizeBrush: Int): Float {
        // chuyển đổi từ pixel sang dp
        return sizeBrush * (resources.displayMetrics.density)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
                return true
            }
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        saveBitmapToStorage(btmView)
        context.startService(serviceIntent)
    }
}