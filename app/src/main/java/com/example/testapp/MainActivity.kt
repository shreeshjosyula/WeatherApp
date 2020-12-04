@file:Suppress("DEPRECATION")

package com.example.testapp

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //val CITY: String = "delhi,in"
    //val API: String = "8118ed6ee68db2debfaaa5a44c832918" // Use your own API key
    //val lat=28.67
    //val lon=77.22
    var lon:Double=28.67
    var lat:Double=77.22



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent=intent
        lon= intent.getDoubleExtra("longitude",lon)
        lat = intent.getDoubleExtra("latitude",lat)
        weatherTask().execute()

    }



    inner class weatherTask() : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {

            var response: String?
            /*website "http://api.openweathermap.org/data/2.5/weather?lat=37.423021&lon=-122.083739&appid=92df465affcf10066bedaae73ab3139e&units=metric"
            for latitude and longitude not secure not working
            https://api.openweathermap.org/data/2.5/weather?q=delhi,in&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918*/
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?lon=$lon&lat=$lat&units=metric&appid=92df465affcf10066bedaae73ab3139e").readText(
                    Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = ""
            }
            return response
        }

        override fun onPostExecute(s: String?) {
            super.onPostExecute(s)

                /* Extracting JSON returns from the API */
            try{
                val jsonObj = JSONObject(s)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat(
                    "dd/MM/yyyy hh:mm a",
                    Locale.ENGLISH
                ).format(Date(updatedAt * 1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(sunset * 1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE


            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
                val Tag:String ="jhjhjh"
                Log.i(Tag, "jkhkjchkjhkjhkjhkjh", e)

            }

        }
    }
}


