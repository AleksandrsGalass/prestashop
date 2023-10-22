package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class QuickViewPopUpPage {

    public final SelenideElement quantityInput = $("#quantity_wanted");
    public final SelenideElement blackColourCheckBox = $("input[title=\"Black\"]");
    public final SelenideElement addToCartButton = $("button[data-button-action=\"add-to-cart\"]");

    private final SelenideElement pageHeader = $("[id^=\"quickview\"]");


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
