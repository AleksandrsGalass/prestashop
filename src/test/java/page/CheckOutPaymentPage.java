package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckOutPaymentPage {

    public final SelenideElement termsAndServiceCheckBox = $("input[id^=\"conditions_to_approve\"] ");
    public final SelenideElement payByCheckCheckBox = $("#payment-option-3-container input");
    public final SelenideElement totalLabel = $("div.cart-summary-line.cart-total span.value");
    public final SelenideElement placeAnOrderButton = $("#payment-confirmation button[type=\"submit\"]");
    private final SelenideElement pageHeader = $("#checkout-payment-step");

    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
