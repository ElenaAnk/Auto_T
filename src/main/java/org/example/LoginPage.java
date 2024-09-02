package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement usernameField = $x("//*[@type='text']");
    private final SelenideElement passwordField = $x("//*[@type='password']");
    private final SelenideElement loginButton = $x("//*[@class='mdc-button__label']");
    private final SelenideElement errorBlock = $x("//*[@class='error-block svelte-uwkxn9']");


    public void login(String username, String password) {
        typeUsernameInField(username);
        typePasswordInField(password);
        clickLoginButton();
    }

    public void typeUsernameInField(String username) {
        usernameField.should(Condition.visible).setValue(username);
    }

    public void typePasswordInField(String password) {
        passwordField.should(Condition.visible).setValue(password);
    }

    public void clickLoginButton() {
        loginButton.should(Condition.visible).click();
    }

    public String getErrorBlockText() {
        return errorBlock.should(Condition.visible).getText().replace("\n", " ");
    }
}
