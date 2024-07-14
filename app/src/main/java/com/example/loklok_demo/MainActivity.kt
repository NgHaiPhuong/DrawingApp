package com.example.loklok_demo

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.loklok_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var container: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleEvent()
    }

    private fun handleEvent() {
        binding.btnLink.setOnClickListener {
            val intent = Intent(this, PairActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSave.setOnClickListener {

        }
    }
}