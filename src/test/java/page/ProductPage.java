package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public final SelenideElement blackColourCheckBox = $("input[title=\"Black\"]");
    public final SelenideElement addToCartButton = $("button[data-button-action=\"add-to-cart\"]");
    private final SelenideElement pageHeader = $("[id=product]");


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
