package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    public final SelenideElement noAccountButton = $(".no-account");

    // Тут можно было не мудрить с xpath, а найти просто хидер и в isLoaded() проверить его текст,
    // так локатор становится более читабельным, и мейнтененс тестов делать проще.
    // Это касается множества PageObject
    // private final SelenideElement pageHeader = $(".page-header");
    private final SelenideElement pageHeader = $(byXpath("//h1[text()[normalize-space()= 'Log in to your account']]"));


    public void isLoaded() {
        pageHeader.shouldBe(visible);

//         pageHeader
//                 .shouldBe(visible)
//                 .shouldHave(text("Log in to your account"));

    }

}
