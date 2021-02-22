package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CallbackTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement element = $("form.form");
        element.$("[data-test-id=name] input").setValue("Анна Белоусова").click();
        element.$("[data-test-id=phone] input").setValue("+78964590086").click();
        element.$("[data-test-id=agreement]").click();
        element.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldNotFillName() {
        open("http://localhost:9999");
        SelenideElement element = $("form.form");
        element.$("[data-test-id=name] input").setValue("").click();
        element.$("[data-test-id=phone] input").setValue("+78964590086").click();
        element.$("[data-test-id=agreement]").click();
        element.$("[type=button]").click();
        element.$("[data-test-id=name].input_invalid").shouldHave(Condition.exactText("Фамилия и имя Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotFillPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Анна Белоусова").click();
        form.$("[data-test-id=phone] input").setValue("").click();
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=phone].input_invalid").shouldHave(Condition.exactText("Мобильный телефон Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitName() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Anna Belousova").click();
        form.$("[data-test-id=phone] input").setValue("+78763349876").click();
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=name].input_invalid").shouldHave(Condition.exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNotSubmitPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Анна Белоусова").click();
        form.$("[data-test-id=phone] input").setValue("Тесттест").click();
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=phone].input_invalid").shouldHave(Condition.exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldNotSubmitAgreement() {
        open("http://localhost:9999");
        SelenideElement element = $("form.form");
        element.$("[data-test-id=name] input").setValue("Анна Белоусова").click();
        element.$("[data-test-id=phone] input").setValue("+78964590086").click();
        element.$("[type=button]").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
