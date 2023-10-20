package demoTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class PrestaShopTest {
    @BeforeSuite
    public void setup(){

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String,Object>();
        prefs.put("credentials_enable_service", Boolean.FALSE);
        prefs.put("autofill.address_enabled", Boolean.FALSE);
        prefs.put("profile.password_manager_enabled", Boolean.FALSE);
        prefs.put("autofill.profile_enabled", Boolean.FALSE);
        chromeOptions.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        WebDriver driver = new ChromeDriver(chromeOptions);
        setWebDriver(driver);
    }

    @Test
    public void smokeTest (){
        open("https://demo.prestashop.com/");
        $("#loadingMessage").should(disappear, Duration.ofSeconds(15));
        switchTo().frame($("#framelive"));
        $("#_desktop_user_info").shouldBe(visible).click();
        $(".no-account").click();
        $("#registration").shouldBe(visible);
        $("#field-id_gender-2").click();
        $("#field-firstname").setValue("Aleksandrs");
        $("#field-lastname").setValue("Galass");
        $("#field-email").setValue("email8@email.com");
        $("#field-password").setValue("email1@email.com");
        $("input[name=\"psgdpr\"]").scrollTo().click();
        $("input[name=\"newsletter\"]").click();
        $("input[name=\"customer_privacy\"]").click();
        $$("input:invalid").shouldHave(size(1));
        $(byText("Save")).click();
        $("#_desktop_user_info span").shouldHave(text("Aleksandrs Galass"),Duration.ofSeconds(10));
        $(byXpath("//*[text()[normalize-space() = 'Accessories']]")).click();
        $(byXpath("//h1[text() = 'Accessories']")).shouldBe(visible);
        Actions action= new Actions(getWebDriver());
        action.dragAndDropBy($("[style=\"left: 0%;\"]"),25,0).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Actions action2= new Actions(getWebDriver());
        action2.dragAndDropBy($("[style=\"left: 100%;\"]"),-150,0).build().perform();
        $("a[href$=\"Color-Black\"]").click();
        $("p[id^=\"facet_label\"]").shouldHave(text("\u20AC18.00 - \u20AC23.00"));
        $$(".filter-block").shouldHave(size(2));
        $$("[class^=\"js-product product\"]").shouldHave(size(3));
        $$("[class^=\"js-product product\"]").get(new Random().nextInt(3)).hover();
        $$("[data-link-action=\"quickview\"]").filter(visible).first().click();
        $("#quantity_wanted").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, "3");
        $("input[title=\"Black\"]").click();
        $("button[data-button-action=\"add-to-cart\"]").click();
        $(byText("Product successfully added to your shopping cart")).shouldBe(visible);
        $("p.product-total span.value").shouldHave(text("\u20AC68.04"));
        $(byText("Continue shopping")).click();
        $$("[class^=\"js-product product\"]").get(new Random().nextInt(3)).click();
        $("input[title=\"Black\"]").click();
        $("button[data-button-action=\"add-to-cart\"]").click();
        $(byText("Product successfully added to your shopping cart")).shouldBe(visible);
        $(byText("Proceed to checkout")).click();
        $(byXpath("//h1[text() = 'Shopping Cart']")).shouldBe(visible);
        $("div.cart-summary-line.cart-total span.value").shouldHave(text("\u20AC90.72"));
        $(byText("Proceed to checkout")).click();
        $("#field-id_country").selectOption("France");
        $("#field-address1").setValue("address");
        $("#field-city").setValue("Riga");
        $("#field-postcode").setValue("12345");
        $(".js-address-form button[type=\"submit\"]").click();
        $(".delivery-options-list button[type=\"submit\"]").click();
        $("input[id^=\"conditions_to_approve\"] ").click();
        $("#payment-option-3-container input").click();
        $("div.cart-summary-line.cart-total span.value").shouldHave(text("\u20AC90.72"));
        $("#payment-confirmation button[type=\"submit\"]").click();
        $(".logout.hidden-sm-down").click();
        $("#_desktop_user_info span").shouldHave(text("Sign in"));
    }
}
