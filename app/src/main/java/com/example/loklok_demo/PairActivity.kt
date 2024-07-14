package com.example.loklok_demo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loklok_demo.databinding.ActivityPairBinding

class PairActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPairBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPairBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleEvent()
    }

    private fun handleEvent() {
        binding.btnback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}