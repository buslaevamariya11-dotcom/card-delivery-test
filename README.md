# Тестирование формы заказа доставки карты

![Java CI with Gradle](https://github.com/buslaevamariya11-dotcom/card-delivery-test/actions/workflows/gradle.yml/badge.svg)

Этот проект автоматизирует тестирование формы доставки банковских карт, включая проверку динамических дат и валидацию полей.

## Как запустить

1. **Склонируйте репозиторий:**
   `git clone https://github.com/buslaevamariya11-dotcom/card-delivery-test.git`

2. **Запустите приложение (SUT):**
   `java -jar ./artifacts/app-card-delivery.jar`

3. **Запустите тесты:**
   `./gradlew test`

## Технологии
* Java 11
* Selenide
* JUnit 5
* GitHub Actions