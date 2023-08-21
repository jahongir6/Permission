package com.example.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

//Permission =ruxsat sorash
//misol uchun internernetdan foydalanish va h.k
//normal permission lar -> internet flashlight vibrate access_networkstate , bluetooth
//Dangerous permission ->  phone camera sms location
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv.setOnClickListener {
            checkPermission()
        }
    }

    //ruxsat brilgan berilamgnini bilish uchun tekshirish
    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.tv.text = "ruxsat bor"
        } else {
            binding.tv.text = "ruxsat yoq"
            requsestPermission()
        }
    }

    //ruxsat sorashni kodi
    fun requsestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_CONTACTS),
            1
        )

    }

    //ruxsat soradik dialog chiqdi va qaysi button bosganimizda ish bolishi
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.tv.text = "ruxsat bor"
            } else {
                binding.tv.text = "ruxsat yoq"
            }
        }
    }
}