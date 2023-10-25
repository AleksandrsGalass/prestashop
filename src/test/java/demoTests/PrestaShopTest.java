package demoTests;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page.*;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class PrestaShopTest {
    HomePage homePage = new HomePage();
    SignInPage signInPage = new SignInPage();
    CreateAccountPage createAccountPage = new CreateAccountPage();
    AccessoriesPage accessoriesPage = new AccessoriesPage();
    QuickViewPopUpPage quickViewPopUpPage = new QuickViewPopUpPage();
    ProductConfirmationPopUpPage productConfirmationPopUpPage = new ProductConfirmationPopUpPage();
    ProductPage productPage = new ProductPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckOutAddressPage checkOutAddressPage = new CheckOutAddressPage();
    CheckOutShippingMethodPage checkOutShippingMethodPage = new CheckOutShippingMethodPage();
    CheckOutPaymentPage checkOutPaymentPage = new CheckOutPaymentPage();
    OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();

    /*
        1) Selenide работает из коробки, у него есть отдельный класс с конфигурацией Configuration, и по дефолту будет Chrome,
            так, что можно не создавать отдельный драйвер. Так же если удалить этот сетап то тоже будет работать кроме maximize.
        2) Сетап лучше выносить в отдельный класс, и тестовые классы уже наследовать от него, для чистоты тестов
    */
    @BeforeSuite
    public void setup() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        final ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("autofill.profile_enabled", Boolean.FALSE);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        WebDriver driver = new ChromeDriver(chromeOptions);
        setWebDriver(driver);
    }

    @AfterTest
    public void tierDown() {
        closeWebDriver();
    }

    @Test
    void smokeTest() {
        String firstName = "FirstName";
        String lastName = "LastName";
        String emailNameBeforeAt = RandomString.make(5);

        /*
            1) Ты используешь PageObject паттерн, тобишь по факту используешь принципы ООП, но у тебя все параметры доступны и из теста.
                Нарушается принцип инкапсуляции, тебе надо было все параметры(элементы которые находишь) сделать приватными
                и заполнять их уже через методы. Например, был бы у тебя в тесте метод register() в который ты передавал бы объект,
                например, NewClient в котором содержались бы все данные, тогда человек, который не знает как у тебя устроено нутро,
                мог бы спокойно писать новые тесты вызвав register() в котором происходит заполнение,
                тест сразу становится лаконичным и логика спрятана в PageObject.
            2) Так же у тебя все действия идут одним полотном, трудно понять где начинается одно действие и заканчивается другое,
               как минимум можно было разделить комментариями для каждого пункта из задания
        */
        open(HomePage.URL);
        homePage.isLoaded();
        homePage.userIsSignedOut();
        homePage.signInButton.click();
        signInPage.isLoaded();
        signInPage.noAccountButton.click();
        createAccountPage.isLoaded();
        createAccountPage.maleGenderRadioButton.click();
        createAccountPage.firsNameInput.setValue(firstName);
        createAccountPage.lastNameInput.setValue(lastName);
        createAccountPage.emailInput.setValue(emailNameBeforeAt + "@email.com");
        createAccountPage.passwordInput.setValue("FirstName@LastName.com");
        createAccountPage.termsCheckBox.scrollTo().click();
        createAccountPage.newsLetterCheckBox.click();
        createAccountPage.privacyCheckBox.click();
        createAccountPage.invalidFieldInputs.shouldHave(size(1));
        createAccountPage.saveButton.click();
        homePage.userIsSignedIn(firstName, lastName);
        homePage.accessoriesButton.click();
        accessoriesPage.isLoaded();


        /*
           1) Слайдер можно было сделать более гибким, на данный момент если начальные значения поменяются на странице,
              сдвиг по пикселям будет выдавать совсем уже другой результат.
           2) По факту в AccessoriesPage должен быть один метод для взаимодействия со слайдером, а не делать это в самом тесте.
              Тест всегда должен быть читабельным и сложная логика должна быть вынесена, в этом случае в AccessoriesPage,
              что бы ты из теста вызвал один метод и передал бы начальное и конечное значение.
        */
        accessoriesPage.priceSliderFromLeft.scrollTo();
        accessoriesPage.moveLeftSliderForwardFor(25);
        /*
           1) Это можно было спрятать в метод, что бы человек который будет использовать этот тест не ломал голову, а что значат эти кодировки.
           2) Надо было написать метод checkPriceRangeLabel(min, max), в который передавались бы значения min, max и внутри уже была бы проверка
        */
        accessoriesPage.priceRangeLabel.shouldHave(text("\u20AC18.00 - \u20AC42.00"));
        accessoriesPage.priceSliderFromRight.scrollTo();
        accessoriesPage.moveRightSliderBackFor(150);
        accessoriesPage.priceRangeLabel.shouldHave(text("\u20AC18.00 - \u20AC23.00"));
        accessoriesPage.filterBlackCheckBox.click();
        accessoriesPage.activeFilters.shouldHave(size(2));
        accessoriesPage.productsFiltered.shouldHave(size(3));
        accessoriesPage.getRandomFilteredProduct().hover();
        accessoriesPage.quickViewButton.click();
        quickViewPopUpPage.isLoaded();
        quickViewPopUpPage.quantityInput.clear();
        quickViewPopUpPage.quantityInput.setValue("3");
        quickViewPopUpPage.blackColourCheckBox.click();
        quickViewPopUpPage.addToCartButton.click();
        productConfirmationPopUpPage.successfulAddMessage.shouldBe(visible);
        productConfirmationPopUpPage.totalLabel.shouldHave(text("\u20AC68.04"));
        productConfirmationPopUpPage.continueShoppingButton.click();
        accessoriesPage.isLoaded();
        accessoriesPage.getRandomFilteredProduct().click();
        productPage.isLoaded();
        productPage.blackColourCheckBox.click();
        productPage.addToCartButton.click();
        productConfirmationPopUpPage.successfulAddMessage.shouldBe(visible);
        productConfirmationPopUpPage.proceedToCheckOutButton.click();
        shoppingCartPage.isLoaded();
        shoppingCartPage.totalLabel.shouldHave(text("\u20AC90.72"));
        shoppingCartPage.proceedToCheckOutButton.click();

        // Тут тоже можно было в CheckOutAddressPage сделать метод в который бы передавался бы объект с адресом
        checkOutAddressPage.isLoaded();
        checkOutAddressPage.countryDropDown.selectOption("France");
        checkOutAddressPage.addressInput.setValue("address");
        checkOutAddressPage.cityInput.setValue("Riga");
        checkOutAddressPage.postCodeInput.setValue("12345");
        checkOutAddressPage.continueButton.click();
        checkOutShippingMethodPage.isLoaded();
        checkOutShippingMethodPage.continueButton.click();
        checkOutPaymentPage.isLoaded();
        checkOutPaymentPage.termsAndServiceCheckBox.click();
        checkOutPaymentPage.payByCheckCheckBox.click();
        checkOutPaymentPage.totalLabel.shouldHave(text("\u20AC90.72"));
        checkOutPaymentPage.placeAnOrderButton.click();
        orderConfirmationPage.isLoaded();
        homePage.signOutButton.click();
        homePage.userIsSignedOut();
    }
}
