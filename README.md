# Приложение Weatherpp
## WeatherApp - мобильное приложение для просмотра текущей погоды и прогноза погоды на будущие дни. Данное приложение получает данные о погоде из открытого API [WeatherAPI](https://www.weatherapi.com/), исходя из текущей геолокации устройства.
___
## Основные функции
1. Получение текущей погоды
   + Возможность увидеть текущие погодные условия (Например: ясно, пасмурно, дождь)
   + Скорость ветра
   + Влажность воздуха
   + Температура воздуха
2. Получение прогноза погоды на будущие дни
   + Получение прогноза погоды на два дня вперед
   + Получение погодных условий на эти дни
   + Получение максимальной и минимальной температуры воздуха
#### На момент разработки приложения, [WeatherAPI](https://www.weatherapi.com/) предоставляло прогноз погоды в диапазоне от 1 до 10 дней. В приложении WeatherApp происходит получение прогноза погоды на неделю.
```kotlin
val response: ForecastApiResponse = client.get(URL_WEATHER_FORECAST) {
    parameter("key", BuildConfig.API_KEY)
    parameter("q", latitude)
    parameter("q", longitude)
    parameter("days", 7) // указание на сколько дней будет получен прогноз
    parameter("aqi", "no")
    parameter("alerts", "no")
}.body()
```
#### На данный момент, открытое API предоставляет прогноз погоды только на 3 дня, включая текущий, вне зависимости от количества дней, переданных в запросе.
___
## Используемые технологии
+ ![Static Badge](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&color=white)
+ ![Static Badge](https://img.shields.io/badge/-Ktor-087CFA?style=for-the-badge&logo=Ktor&logoColor=white)
+ ![Static Badge](https://img.shields.io/badge/kotlinx%20serialization-7F52FF?style=for-the-badge&logoColor=white)
+ ![Static Badge](https://img.shields.io/badge/-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
+ MVI
___
## Установка
1. Склонировать репозиторий
    + С помощью командной строки перейти в папку, где будет храниться проект
   ```
   cd C:\папка
   ```
   + Склонировать проект
   ```
   git init
   git clone https://github.com/Motya70k/WeatherApp
   ```
2. Открыть проект в Android Studio
3. Получить API_KEY на [WeatherAPI](https://www.weatherapi.com/)
4. В local.properties создать переменную API_KEY
   ```
   sdk.dir=Ваш путь до Android SDK
   API_KEY=Ваш API_KEY, полученный с WeatherAPI
   ```
5. Синхронизировать проект
6. Запустить приложение на эмуляторе или реальном смартфоне
___
#### Для корректной работы необходимо предоставить разрешение на получение местоположения устройства.
