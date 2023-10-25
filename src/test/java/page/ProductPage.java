package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    // тут можно текст брать в одиночные кавычки, тогда не нужно будет escape character использовать(это во многих местах)
    // "input[title='Black']"
    public final SelenideElement blackColourCheckBox = $("input[title=\"Black\"]");
    public final SelenideElement addToCartButton = $("button[data-button-action=\"add-to-cart\"]");
    // можно использовать selenide подход для локатора
    // "#product", кстати есть места, где ты это используешь
    private final SelenideElement pageHeader = $("[id=product]");


    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
