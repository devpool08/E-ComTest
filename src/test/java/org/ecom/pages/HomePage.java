package org.ecom.pages;

import org.ecom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@SuppressWarnings({"unused"})
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//section[@id='categorymenu']//ul[@class='nav-pills categorymenu']/li")
    private List<WebElement> categoryMenuItems;
    @FindBy(xpath = "//ul[@class='thumbnails row']//li")
    private List<WebElement> productItems;
    @FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12']")
    private List<WebElement> productList;
    @FindBy(xpath = "//div[@class='block_7']//a[@class='dropdown-toggle']")
    private WebElement cartDropdown;

    public List<WebElement> getCategoryMenuItems() {
        wait.until(ExpectedConditions.visibilityOf(categoryMenuItems.get(0)));
        return categoryMenuItems;
    }
    public List<WebElement> getProductItems() {
        wait.until(ExpectedConditions.visibilityOf(productItems.get(0)));
        return productItems;
    }
    public List<WebElement> getProductList() {
        wait.until(ExpectedConditions.visibilityOf(productList.get(0)));
        return productList;
    }

    public void navigateToCategory(String categoryName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(categoryMenuItems));
        for (WebElement category : categoryMenuItems) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                category.click();
                break;
            }
        }
    }
    public String getProductName(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.findElement(By.xpath(".//a[@class='prdocutname']")).getText();
    }
    public String getProductURL(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        //noinspection deprecation
        return element.findElement(By.xpath(".//a[@class='prdocutname']")).getAttribute("href");
    }
    public String getProductPrice(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.findElement(By.xpath(".//div[@class='price']")).getText();
    }
    public void addToCart(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        WebElement addToCartButton = element.findElement(By.xpath(".//a[@class='productcart']"));
        addToCartButton.click();
    }
    public void clickCartDropdown() {
        wait.until(ExpectedConditions.visibilityOf(cartDropdown));
        cartDropdown.click();
    }
    public List<WebElement> getCheckoutProductList() {
        List<WebElement> productList=driver.findElements(By.xpath("//div[@class='container-fluid cart-info product-list']//table[@class='table table-striped table-bordered']//tr"));
        wait.until(ExpectedConditions.visibilityOf(productList.get(1)));
        return productList;
    }
    public String getCheckoutProductName(WebElement product) {
        return product.findElement(By.xpath(".//td[@class='align_left']/a")).getText();
    }
    public String getCheckoutProductPrice(WebElement product) {
        return product.findElement(By.xpath(".//td[4]")).getText();
    }
}
