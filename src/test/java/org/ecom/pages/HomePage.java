package org.ecom.pages;

import org.ecom.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//section[@id='categorymenu']//ul[@class='nav-pills categorymenu']/li")
    private List<WebElement> categoryMenuItems;

    public List<WebElement> getCategoryMenuItems() {
        return categoryMenuItems;
    }
}
