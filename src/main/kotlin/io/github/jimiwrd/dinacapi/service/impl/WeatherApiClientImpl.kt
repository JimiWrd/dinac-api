package io.github.jimiwrd.dinacapi.service.impl

import io.github.jimiwrd.dinacapi.service.WeatherApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.net.URI
import java.time.LocalDate

@Service
class WeatherApiClientImpl @Autowired constructor(private val restTemplate: RestTemplate): WeatherApiClient {

    @Value("\${weather.api.baseUrl}")
    private lateinit var baseURI: String

    @Value("\${WEATHER_API_KEY}")
    private lateinit var apiKey: String

    override fun callToWeatherApi(location: String): ResponseEntity<String> {
        val uri = buildURI(location);
        println(uri)
        return restTemplate.getForEntity<String>(uri)
    }

    private fun buildURI(location: String): URI {
        val uriString = "${baseURI}/${location}/${LocalDate.now()}?unitGroup=uk&key=${apiKey}&include=days&elements=description,precip,tempmax,tempmin"
        return URI.create(uriString)
    }
}