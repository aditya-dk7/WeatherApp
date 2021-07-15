package dk7aditya.weatherapp.network.data

import dk7aditya.weatherapp.BuildConfig
import dk7aditya.weatherapp.network.response.CurrentWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q")
        location: String,
        @Query("aqi")
        airQualityIndex: String = "yes"
    ): CurrentWeatherResponse

    companion object{
        operator fun invoke(): WeatherApiService{
            val requestInterceptor = Interceptor{ chain ->
               val url = chain.request()
                   .url()
                   .newBuilder()
                   .addQueryParameter("key", BuildConfig.WEATHER_KEY)
                   .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)

        }
    }

}