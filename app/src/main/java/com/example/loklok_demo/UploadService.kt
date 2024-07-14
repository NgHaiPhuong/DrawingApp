package com.example.loklok_demo

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.AsyncTask
import android.os.IBinder
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class UploadService : Service() {
    private val myFile = File("data/data/com.elselse.loklok/cache/", "local.png")
    var myRef: StorageReference? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        uploadToFirebase()
        return START_NOT_STICKY
    }

    @SuppressLint("StaticFieldLeak")
    private fun uploadToFirebase() {
        val loc : String
        val prefs1 : SharedPreferences = getSharedPreferences("PAIR", MODE_PRIVATE)
        val FRIEND : Int = prefs1.getInt("FRIEND",0)
        if(FRIEND != 0){
            loc = FRIEND.toString()
            myRef = FirebaseStorage.getInstance().getReference("${loc}.png")
            object : AsyncTask<Void, String, Void>(){
                override fun doInBackground(vararg params: Void?): Void? {
                    if(myFile.exists()){
                        myRef!!.putFile(Uri.fromFile(myFile)).addOnSuccessListener {
                            myFile.delete()
                            stopSelf()
                        }
                    }
                    else stopSelf()
                    return null
                }
            }.execute(null, null, null)

        }
    }
}



