package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AccessoriesPage {

    public final SelenideElement filterBlackCheckBox = $("a[href$=\"Color-Black\"]");

    // Странные локаторы, можно было использовать ui-slider-handle класс для нахождения,
    // по этим стилям можно что угодно найти при изменениях на странице, а потом ломать голову почему тест не работает
    public final SelenideElement priceSliderFromLeft = $("[style=\"left: 0%;\"]");
    public final SelenideElement priceSliderFromRight = $("[style=\"left: 100%;\"]");
    public final SelenideElement priceRangeLabel = $("p[id^=\"facet_label\"]");
    public final ElementsCollection activeFilters = $$(".filter-block");
    public final ElementsCollection productsFiltered = $$("[class^=\"js-product product\"]");
    public final SelenideElement quickViewButton = $$("[data-link-action=\"quickview\"]").filter(visible).first();
    private final SelenideElement pageHeader = $(byXpath("//h1[text()[normalize-space()= 'Accessories']]"));
    private final SelenideElement overlay = $(".faceted-overlay");

    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

    // Два почти одинаковых метода для сдвига слайдера, а это дублирование кода.
    // Ты используешь селениум Actions, можно было использовать обертку селенида
    // actions().dragAndDropBy(element, xOffset, yOffset).perform();
    public void moveLeftSliderForwardFor(int numberOfPixels) {
        Actions actions = new Actions(getWebDriver());
        actions.dragAndDropBy(priceSliderFromLeft, numberOfPixels, 0).build().perform();
        overlay.shouldNot(exist, Duration.ofSeconds(5));
    }

    public void moveRightSliderBackFor(int numberOfPixels) {
        Actions actions = new Actions(getWebDriver());
        actions.dragAndDropBy(priceSliderFromRight, numberOfPixels * -1, 0).build().perform();
        overlay.shouldNot(exist, Duration.ofSeconds(5));
    }

    public SelenideElement getRandomFilteredProduct() {
        return productsFiltered.get(new Random().nextInt(3));
    }

}
