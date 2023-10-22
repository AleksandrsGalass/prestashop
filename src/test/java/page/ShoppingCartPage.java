package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage {

    public final SelenideElement proceedToCheckOutButton = $(byText("Proceed to checkout"));
    public final SelenideElement totalLabel = $("div.cart-summary-line.cart-total span.value");
    private final SelenideElement pageHeader = $(byXpath("//h1[text() = 'Shopping Cart']"));


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
