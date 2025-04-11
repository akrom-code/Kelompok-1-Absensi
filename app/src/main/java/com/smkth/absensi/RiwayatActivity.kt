package com.smkth.absensi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smkth.absensi.databinding.ActivityRiwayatBinding

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding
    private var sharepref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharepref = applicationContext.getSharedPreferences("absen-app", Context.MODE_PRIVATE)

        binding.btnSimpan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.txtNama.text = "Nama: " + sharepref?.getString("key-nama", "Tidak ada data")
        binding.txtNis.text = "NIS: " + sharepref?.getString("key-nis", "Tidak ada data")
        binding.txtKelas.text = "Kelas: " + sharepref?.getString("key-kelas", "Tidak ada data")
        binding.txtAbsen.text = "Absen: " + sharepref?.getString("key-absen", "Tidak ada data")
        binding.txtLokasi.text = "Lokasi: " + sharepref?.getString("key-lokasi", "Tidak ada data")
        binding.txtKeterangan.text = "Keterangan: " + sharepref?.getString("key-keterangan", "Tidak ada data")

        binding.btnHapus.setOnClickListener {
            val editor = sharepref?.edit()
            editor?.clear()
            editor?.apply()

            binding.txtNama.text = "Nama: "
            binding.txtNis.text = "NIS: "
            binding.txtKelas.text = "Kelas: "
            binding.txtAbsen.text = "Absen: "
            binding.txtLokasi.text = "Lokasi: "
            binding.txtKeterangan.text = "Keterangan: "

        }
    }
}