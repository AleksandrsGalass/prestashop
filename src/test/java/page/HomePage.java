package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class HomePage {


    public static final String URL = ("https://demo.prestashop.com/");
    public final SelenideElement signInButton = $("#_desktop_user_info");
    public final SelenideElement signOutButton = $(".logout.hidden-sm-down");
    public final SelenideElement accessoriesButton = $(byXpath("//*[text()[normalize-space() = 'Accessories']]"));
    private final SelenideElement loadMessage = $("#loadingMessage");
    private final SelenideElement iFrame = $("#framelive");

    public void isLoaded() {
        loadMessage.should(disappear, Duration.ofSeconds(15));
        switchTo().frame(iFrame);

    }

    public void userIsSignedOut() {
        signInButton.shouldBe(visible);
    }

    public void userIsSignedIn(String name, String lastName) {
        signInButton.shouldHave(text(name + " " + lastName));
    }
}
