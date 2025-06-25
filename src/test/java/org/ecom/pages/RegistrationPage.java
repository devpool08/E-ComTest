package org.ecom.pages;

import org.ecom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"unused"})
public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='AccountFrm_firstname']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_lastname']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='AccountFrm_address_1']")
    private WebElement addressInput;
    @FindBy(xpath = "//input[@id='AccountFrm_city']")
    private WebElement cityInput;
    @FindBy(xpath = "//input[@id='AccountFrm_postcode']")
    private WebElement postcodeInput;
    @FindBy(xpath = "//select[@id='AccountFrm_zone_id']")
    private WebElement stateDropdown;
    @FindBy(xpath = "//select[@id='AccountFrm_country_id']")
    private WebElement countryDropdown;
    @FindBy(xpath = "//input[@id='AccountFrm_loginname']")
    private WebElement usernameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='AccountFrm_confirm']")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//input[@id='AccountFrm_agree']")
    private WebElement agreeCheckbox;
    @FindBy(xpath = "//button[normalize-space()='Continue']")
    private WebElement continueButton;


    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);
    }

    public void enterAddress(String address) {
        wait.until(ExpectedConditions.visibilityOf(addressInput));
        addressInput.sendKeys(address);
    }

    public void enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityInput));
        cityInput.sendKeys(city);
    }

    public void enterPostcode(String postcode) {
        wait.until(ExpectedConditions.visibilityOf(postcodeInput));
        postcodeInput.sendKeys(postcode);
    }

    public List<String> getStateOptions() {
        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        return stateDropdown.findElements(By.tagName("option")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectState(String state) {
        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        stateDropdown.sendKeys(state);
    }

    public List<String> getCountryOptions() {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        return countryDropdown.findElements(By.tagName("option")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectCountry(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        countryDropdown.sendKeys(country);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput));
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void agreeToTerms() {
        wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));
        if (!agreeCheckbox.isSelected()) {
            agreeCheckbox.click();
        }
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
}
