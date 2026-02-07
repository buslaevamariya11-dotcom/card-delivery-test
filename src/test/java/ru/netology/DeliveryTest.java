package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeAll
    static void setUpAll() {
        // Настройки для стабильности в CI
        Configuration.pageLoadTimeout = 60000;
    }

    @Test
    void shouldTestSuccessfulFormSubmission() {
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        // Город
        $("[data-test-id='city'] input").setValue("Москва");

        // Дата: используем надежный метод очистки
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);

        // ФИО и Телефон
        $("[data-test-id='name'] input").setValue("Иван Иванов-Петров");
        $("[data-test-id='phone'] input").setValue("+79271234567");

        // Согласие
        $("[data-test-id='agreement']").click();

        // Отправка формы
        $$("button").find(Condition.exactText("Забронировать")).click();

        // Проверка результата
        $("[data-test-id='notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }
}