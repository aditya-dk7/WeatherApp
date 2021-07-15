package dk7aditya.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dk7aditya.weatherapp.network.data.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var displayContent: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchContent: EditText = findViewById(R.id.searchContent)
        val searchButton: Button = findViewById(R.id.searchButton)
        displayContent = findViewById(R.id.displayContent)
        searchButton.setOnClickListener{
            if(searchContent.text.isEmpty()){
                Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show()
            }else{
                callWeatherApi(searchContent.text.toString())
            }
        }
    }

    private fun callWeatherApi(cityName: String){

        val weatherApiServiceResponse = WeatherApiService()
        GlobalScope.launch(Dispatchers.Main) {
            try{
                val currentWeatherResponse = weatherApiServiceResponse.getCurrentWeather(cityName, "yes")
                displayContent!!.text = currentWeatherResponse.toString()
            }catch (e: Exception){
                e.printStackTrace();
            }

        }

    }

}