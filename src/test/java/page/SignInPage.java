package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    public final SelenideElement noAccountButton = $(".no-account");

    private final SelenideElement pageHeader = $(byXpath("//h1[text()[normalize-space()= 'Log in to your account']]"));


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
