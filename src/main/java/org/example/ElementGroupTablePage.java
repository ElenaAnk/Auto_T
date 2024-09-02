package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;


public class ElementGroupTablePage {

    private final SelenideElement root;

    public ElementGroupTablePage(SelenideElement root) {
        this.root = root;
    }


    // Group table block
    public String getTitle() {
        return root.$x("./td[2]").should(Condition.visible).getText();
    }

    public String getStatus() {
        return root.$x("./td[3]").should(Condition.visible).getText();
    }

    public void clickTrashIcon() {
        root.$x("./td/button[text()='delete']").should(Condition.visible).click();
        root.$x("./td/button[text()='restore_from_trash']")
                .should(Condition.visible, Duration.ofSeconds(30));
    }

    public void clickRestoreFromTrashIcon() {
        root.$x("./td/button[text()='restore_from_trash']").should(Condition.visible).click();
        root.$x("./td/button[text()='delete']").should(Condition.visible, Duration.ofSeconds(30));
    }


    // Student table block
    public void clickOnCreatingNewLoginsStudentsIcon() {
        root.$x(".//*[@class='material-icons mdc-button__icon']").should(Condition.visible).click();
    }

    public void waitForChangeNumberOfLoginsStudents(int number) {
        root.$x("./td[4]//span[text()='%s']".formatted(number)).should(Condition.visible);
    }

    public void clickOnStudentsIdentitiesIcon() {
        root.$x(".//td/button[contains(., 'zoom_in')]").should(Condition.visible).click();
    }
}