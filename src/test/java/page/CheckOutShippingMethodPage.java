package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckOutShippingMethodPage {

    public final SelenideElement continueButton = $(".delivery-options-list button[type=\"submit\"]");
    private final SelenideElement pageHeader = $("#checkout-delivery-step");


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
