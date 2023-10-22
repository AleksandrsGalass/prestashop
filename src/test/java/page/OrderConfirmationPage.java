package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderConfirmationPage {

    private final SelenideElement pageHeader = $("#content-hook_order_confirmation");

    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
