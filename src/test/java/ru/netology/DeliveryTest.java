package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
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

    @Test
    void shouldTestSuccessfulFormSubmission() {

        Configuration.headless = true;

        open("http://localhost:9999");
        String planningDate = generateDate(3);

        // 1. Город - прямой ввод согласно Задаче №1
        $("[data-test-id='city'] input").setValue("Москва");

        // 2. Дата - очистка и ввод
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);

        // 3. ФИО и Телефон
        $("[data-test-id='name'] input").setValue("Иван Иванов-Петров");
        $("[data-test-id='phone'] input").setValue("+79271234567");

        // 4. Согласие (чекбокс)
        $("[data-test-id='agreement']").click();

        // 5. Кнопка "Забронировать"
        // Используем поиск по тексту кнопки, так как у нее может не быть data-test-id
        $$("button").find(Condition.text("Забронировать")).click();

        // 6. Проверка результата (ждем не более 15 секунд по условию)
        $("[data-test-id='notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }
}