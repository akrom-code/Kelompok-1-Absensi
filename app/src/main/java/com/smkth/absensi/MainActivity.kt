package com.smkth.absensi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smkth.absensi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnMasuk.setOnClickListener {
            Intent(this, HomeAbsenActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnKeluar.setOnClickListener {
            Intent(this, HomeAbsenActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnIzin.setOnClickListener {
            Intent(this, HomeAbsenActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnListRiwayat.setOnClickListener {
            Intent(this, RiwayatActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
}