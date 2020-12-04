package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LatLon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lat_lon)
        var lat: EditText = findViewById(R.id.latitude_et)
        var lon: EditText = findViewById(R.id.longitude_et)
        val w_btn: Button = findViewById(R.id.weather_btn)
        w_btn.setOnClickListener {

            if (lat.text.isEmpty() || lon.text.isEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                val longitude: Double = lon.getText().toString().toDouble()
                val latitude: Double = lat.getText().toString().toDouble()
                intent.putExtra("longitude", longitude)
                intent.putExtra("latitude", latitude)
                startActivity(intent)

            }


        }

    }}

