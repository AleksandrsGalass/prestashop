package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateAccountPage {

    public final SelenideElement femaleGenderRadioButton = $("#field-id_gender-2");
    public final SelenideElement maleGenderRadioButton = $("#field-id_gender-1");
    public final SelenideElement firsNameInput = $("#field-firstname");
    public final SelenideElement lastNameInput = $("#field-lastname");
    public final SelenideElement emailInput = $("#field-email");
    public final SelenideElement passwordInput = $("#field-password");
    public final SelenideElement termsCheckBox = $("input[name=\"psgdpr\"]");
    public final SelenideElement newsLetterCheckBox = $("input[name=\"newsletter\"]");
    public final SelenideElement privacyCheckBox = $("input[name=\"customer_privacy\"]");
    public final SelenideElement saveButton = $(byText("Save"));
    private final SelenideElement pageHeader = $(byXpath("//h1[text()[normalize-space()= 'Create an account']]"));
    public ElementsCollection invalidFieldInputs = $$("input:invalid");

    public void isLoaded() {
        pageHeader.shouldBe(visible);

    }

}
