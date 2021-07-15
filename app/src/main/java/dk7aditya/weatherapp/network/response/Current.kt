package dk7aditya.weatherapp.network.response


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("air_quality")
    val airQuality: AirQuality,
    val condition: Condition,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    val uv: Int,
    @SerializedName("wind_kph")
    val windKph: Double
)