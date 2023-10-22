package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckOutAddressPage {

    public final SelenideElement countryDropDown = $("#field-id_country");
    public final SelenideElement addressInput = $("#field-address1");
    public final SelenideElement cityInput = $("#field-city");
    public final SelenideElement postCodeInput = $("#field-postcode");
    public final SelenideElement continueButton = $(".js-address-form button[type=\"submit\"]");
    private final SelenideElement pageHeader = $("#checkout-addresses-step");

    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
