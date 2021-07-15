package dk7aditya.weatherapp.network.response


data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)