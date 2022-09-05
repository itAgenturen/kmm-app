package com.example.myfirstkmmapp

import com.example.myfirstkmmapp.models.RocketLaunch
import com.example.myfirstkmmapp.utils.daysUntilNewYear
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    suspend fun greeting(): String {
        val rockets: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${Platform().platform}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎅🏼 " +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}