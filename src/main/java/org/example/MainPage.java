package org.example;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement usernameInMenu = $("nav li.mdc-menu-surface--anchor a");
    private final SelenideElement createGroupButton = $x("//*[@id='create-btn']");
    private final SelenideElement groupNameField = $x("//*[@type='text']");
    private final SelenideElement submitButtonOnModalWindow = $("form div.submit button");
    private final SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']"
            + "//ancestor::div[contains(@class, 'form-modal-header')]//button");
    private final SelenideElement creatingNewLoginsStudentsInput = $x("//*[@type='number']");
    private final SelenideElement saveNumberNewLoginsStudents = $(
            "div#generateStudentsForm-content div.submit button");
    private final SelenideElement closeNewLoginsStudentsForm = $x(
            "//h2[@id='generateStudentsForm-title']/../button");
    private final ElementsCollection rowsInGroupTable = $$x("//table[@aria-label='Tutors list']/tbody/tr");
    private final ElementsCollection rowsInStudentsIdentitiesTable = $$x(
            "//table[@aria-label='User list']/tbody/tr");
    private final SelenideElement profileButton = $x(
            "//nav//li[contains(@class,'mdc-menu-surface--anchor')]//span[text()='Profile']");

    public void clickProfileButton() {
        usernameInMenu.should(Condition.visible).click();
        profileButton.should(Condition.visible).click();
    }


    // Group table block
    public SelenideElement waitGroupTitleByText(String title) {
        return $x(String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title))
                .should(Condition.visible);
    }

    public void createGroup(String groupName) {
        createGroupButton.should(Condition.visible).click();
        groupNameField.should(Condition.visible).setValue(groupName);
        groupNameField.should(value(groupName));
        submitButtonOnModalWindow.should(Condition.visible).click();
        waitGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.should(Condition.visible).click();
        closeCreateGroupIcon.should(Condition.hidden);
    }

    public String getUsernameLabelText() {
        return usernameInMenu.should(Condition.visible).getText().replace("\n", " ");
    }

    public void clickTrashIconOnGroupWithTitle(String title) {
        getRowByTitle(title).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getRowByTitle(title).clickRestoreFromTrashIcon();
    }

    public String getStatusOfGroupWithTitle(String title) {
        return getRowByTitle(title).getStatus();
    }

    private ElementGroupTablePage getRowByTitle(String title) {
        return rowsInGroupTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(ElementGroupTablePage::new)
                .filter(x -> x.getTitle().equals(title))
                .findFirst()
                .orElseThrow();
    }

    // Students table block
    public void clickOnCreatingNewLoginsStudentsByTitle(String title) {
        getRowByTitle(title).clickOnCreatingNewLoginsStudentsIcon();
    }

    public void enteringTheNumberOfNewLoginsStudents(int studentQuantity) {
        creatingNewLoginsStudentsInput.should(Condition.visible).setValue(String.valueOf(studentQuantity));
    }

    public void clickSaveNumberNewLoginsStudents(){
        saveNumberNewLoginsStudents.should(Condition.visible).click();
    }


    public void clickCloseNewLoginsStudentsForm() {
        closeNewLoginsStudentsForm.should(Condition.visible).click();
        closeNewLoginsStudentsForm.should(Condition.hidden);
    }

    public void clickOnStudentsIdentitiesByTitle(String title) {
        getRowByTitle(title).clickOnStudentsIdentitiesIcon();
    }

    public String getStudentUsernameByIndex(int studentIndex){
        return rowsInStudentsIdentitiesTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(ElementStudentTablePage::new)
                .toList().get(studentIndex).getName();
    }

    public void waitForChangeNumberOfLoginsStudents(String groupName, int quantityStudent) {
        getRowByTitle(groupName).waitForChangeNumberOfLoginsStudents(quantityStudent);
    }

    public void clickTrashIconOnStudentByUsername(String username) {
        getStudentRowUsername(username).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnStudentByUsername(String username) {
        getStudentRowUsername(username).clickRestoreFromTrashIcon();
    }

    public String getStatusOfStudentByUsername(String username) {
        return getStudentRowUsername(username).getStatus();
    }

    private ElementStudentTablePage getStudentRowUsername(String name) {
        return rowsInStudentsIdentitiesTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(ElementStudentTablePage::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }
}