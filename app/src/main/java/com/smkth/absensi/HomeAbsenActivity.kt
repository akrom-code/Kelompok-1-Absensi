package com.smkth.absensi

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smkth.absensi.databinding.ActivityHomeAbsenBinding
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import android.location.Geocoder
import java.util.Locale



class HomeAbsenActivity : AppCompatActivity() {

    private var sharepref: SharedPreferences? = null
    private lateinit var binding: ActivityHomeAbsenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeAbsenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharepref = applicationContext.getSharedPreferences("absen-app", Context.MODE_PRIVATE)
        val editor = sharepref?.edit()

        getLocation()


        binding.btnSubmitAbsen.setOnClickListener {
            editor?.putString("key-nama", binding.edtNama.text.toString())
            editor?.putString("key-nis", binding.edtNis.text.toString())
            editor?.putString("key-kelas", binding.edtKelas.text.toString())
            editor?.putString("key-absen", binding.edtAbsen.text.toString())
            editor?.putString("key-lokasi", binding.edtLokasi.text.toString())
            editor?.putString("key-keterangan", binding.edtKeterangan.text.toString())
            editor?.apply()
            Toast.makeText(this, "Data tersimpan",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
        }
        binding.btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    @Suppress("DEPRECATION")
    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.getFusedLocationProviderClient(this).lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        if (!addresses.isNullOrEmpty()) {
                            binding.edtLokasi.setText(addresses[0].getAddressLine(0)) // Alamat lengkap
                        } else {
                            binding.edtLokasi.setText("Lokasi tidak ditemukan")
                        }
                    } else {
                        showToast("Gagal mendapatkan lokasi")
                    }
                }
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation()
        } else {
            showToast("Izin lokasi diperlukan untuk mengisi alamat otomatis")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
