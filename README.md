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
Inject **WeatherStationRepositoryFactory** to ViewModel
```kotlin
internal class MainViewModel @Inject constructor(
    private val weatherRepositoryFactory: WeatherStationRepositoryFactory,
) 
``` 
Initialize the variable **WeatherStationRepository**
```kotlin
private var _weatherRepository: WeatherStationRepository? = null
``` 
Use factory to create repository (set your host address)
```kotlin
_weatherRepository = weatherRepositoryFactory.get("http://your-host:your-port")
``` 