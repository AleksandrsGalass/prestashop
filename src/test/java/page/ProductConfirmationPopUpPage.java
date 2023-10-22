package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProductConfirmationPopUpPage {

    public final SelenideElement successfulAddMessage = $(byText("Product successfully added to your shopping cart"));
    public final SelenideElement totalLabel = $("p.product-total span.value");
    public final SelenideElement continueShoppingButton = $(byText("Continue shopping"));
    public final SelenideElement proceedToCheckOutButton = $(byText("Proceed to checkout"));

}
