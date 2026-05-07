# Weather Station Client

A library for seamless interaction with the weather station API.

[![](https://jitpack.io/v/ArtemBotnev/weather-station-client.svg)](https://jitpack.io/#ArtemBotnev/weather-station-client)

## Installation

### 1. Configure Repositories
Add the **JitPack** repository to your `settings.gradle.kts` file (or the root `build.gradle.kts`):

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add Dependency
Add the library to your module's `build.gradle.kts` dependencies block:

```kotlin
dependencies {
    // Weather station client dependency
    // Replace 'Tag' with a specific version (e.g., '1.0.0')
    implementation("com.github.ArtemBotnev:weather-station-client:Tag")
}
```

### 3. How to use
Implement **WeatherStationRepository**, set your base url, create client and override function for your needs
```kotlin
internal class WeatherStationRepositoryImpl(baseUrl: String) : WeatherStationRepository  {

    private val client = WeatherStationClient(baseUrl)
    private val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getMeasurement(deviceId: Int): Measurement? = try {
        json.decodeFromString(client.getMeasurement(deviceId).body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDevices(): List<Device> = try {
        json.decodeFromString(client.getDeviseList().body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDeviceDailyErrors(deviceId: Int): DeviceDailyErrors? = try {
        json.decodeFromString(client.getDeviceDailyErrors(deviceId).body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDeviceListDailyErrors(): List<DeviceDailyErrors> = try {
        json.decodeFromString(client.getDeviceListDailyErrors().body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }
}
``` 