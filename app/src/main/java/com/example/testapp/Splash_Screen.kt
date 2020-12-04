package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screen : AppCompatActivity() {

    private val SPLASH_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        Handler().postDelayed({
            startActivity(Intent (this, login::class.java))
            finish()
        },SPLASH_TIME)
    }
}