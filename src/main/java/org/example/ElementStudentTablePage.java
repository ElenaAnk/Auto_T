package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class ElementStudentTablePage {

    private final SelenideElement root;

    public ElementStudentTablePage(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.$x("./td[2]").should(Condition.visible).getText();
    }

    public String getStatus() {
        return root.$x("./td[4]").should(Condition.visible).getText();
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
}
