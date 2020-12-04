package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login_btn:Button =findViewById(R.id.login_btn)
        val username :EditText=findViewById(R.id.username_et)
        val password:EditText=findViewById(R.id.password_et)
        login_btn.setOnClickListener {
           val status:String= if(username.text.toString().equals("WeatherApp") && password.text.toString().equals("12345"))"Logged In Successfully" else "Login Failed"
            if(username.text.toString().equals("WeatherApp") && password.text.toString().equals("12345")){
                val intent= Intent(this,LatLon::class.java)
                startActivity(intent)
                finish()
            }

            Toast.makeText(this,status,Toast.LENGTH_SHORT).show()
        }


    }
}
