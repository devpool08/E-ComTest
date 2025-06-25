package org.ecom.pages;

import org.ecom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@SuppressWarnings({"unused"})
public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='container-fluid cart-info product-list']//table[@class='table table-striped table-bordered']//tr")
    private List<WebElement> tableRows;

    public List<WebElement> getTableRows(){
        wait.until(ExpectedConditions.visibilityOf(tableRows.get(0)));
        return tableRows;
    }

    public String getProductName(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.findElement(By.xpath(".//td[@class='align_left']/a")).getText();
    }
    public String getProductPrice(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.findElement(By.xpath(".//td[@class='align_right']")).getText();
    }



}
