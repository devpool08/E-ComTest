package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.pages.CheckoutPage;
import org.ecom.pages.HomePage;
import org.ecom.utils.data.TestData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.ecom.utils.NavigationUtils.openPage;
import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal"})
public class ProductSelectionAndCartAdditionTest extends BaseTest {
    private HomePage homePage;
    private CheckoutPage checkoutPage;

    @Test
    public void prerequisite() {
        try {
            openPage(getThreadLocalDriver(), properties.getProperty("homepage.url"));
            homePage = new HomePage(getThreadLocalDriver());
            log.info("Homepage is opened successfully");
        } catch (Exception e) {
            log.error("Failed to open homepage: {}", e.getMessage());
            throw new RuntimeException("Failed to open homepage: " + e);
        }
    }

    @Test(dependsOnMethods = "prerequisite")
    public void selectRandomProductAndAddToCart() {
        try {
            List<WebElement> products = homePage.getProductList();
            if (products.isEmpty()) {
                log.error("No products found on the homepage");
                throw new RuntimeException("No products found on the homepage");
            }
            for (WebElement product : products) {
                log.info("Product name: {}", homePage.getProductName(product));
                log.info("Product price: {}", homePage.getProductPrice(product));
                log.info("Product url: {}", homePage.getProductURL(product));
            }
            int product1 = TestData.getRandomNumber(0, products.size() - 1);
            WebElement randomProduct1 = products.get(product1);
            String expectedProductName1 = homePage.getProductName(randomProduct1);
            String expectedProductPrice1 = homePage.getProductPrice(randomProduct1);
            homePage.addToCart(randomProduct1);
            log.info("Random product selected and added to cart successfully: {} having price {}", expectedProductName1, expectedProductPrice1);
            int product2 = TestData.getRandomNumber(0, products.size() - 1);
            WebElement randomProduct2 = products.get(product2);
            String expectedProductName2 = homePage.getProductName(randomProduct2);
            String expectedProductPrice2 = homePage.getProductPrice(randomProduct2);
            homePage.addToCart(randomProduct2);
            log.info("Random product selected and added to cart successfully: {} having price {}", expectedProductName2, expectedProductPrice2);
            homePage.clickCartDropdown();
            checkoutPage = new CheckoutPage(getThreadLocalDriver());
            List<WebElement> cartItems = checkoutPage.getTableRows();
            WebElement firstCartItem = cartItems.get(1);
            String actualProductName1 = checkoutPage.getProductName(firstCartItem);
            String actualProductPrice1 = checkoutPage.getProductPrice(firstCartItem);
            softAssert.assertTrue(actualProductName1.replaceAll("\\s+", "").equalsIgnoreCase(expectedProductName1.replaceAll("\\s+", "")),
                    "Product name in cart does not match: expected " + expectedProductName1 + ", but got " + actualProductName1);
            softAssert.assertTrue(actualProductPrice1.replaceAll("\\s+", "").equalsIgnoreCase(expectedProductPrice1.replaceAll("\\s+", "")),
                    "Product price in cart does not match: expected " + expectedProductPrice1 + ", but got " + actualProductPrice1);
            WebElement secondCartItem = cartItems.get(2);
            String actualProductName2 = checkoutPage.getProductName(secondCartItem);
            String actualProductPrice2 = checkoutPage.getProductPrice(secondCartItem);
            softAssert.assertTrue(actualProductName2.replaceAll("\\s+", "").equalsIgnoreCase(expectedProductName2.replaceAll("\\s+", "")),
                    "Product name in cart does not match: expected " + expectedProductName2 + ", but got " + actualProductName2);
            softAssert.assertTrue(actualProductPrice2.replaceAll("\\s+", "").equalsIgnoreCase(expectedProductPrice2.replaceAll("\\s+", "")),
                    "Product price in cart does not match: expected " + expectedProductPrice2 + ", but got " + actualProductPrice2);
            softAssert.assertAll();
            Thread.sleep(4000);
            log.info("Random product selected and added to cart successfully");
        } catch (Exception e) {
            log.error("Failed to select product and add to cart: {}", e.getMessage());
            throw new RuntimeException("Failed to select product and add to cart: " + e);
        }
    }
}
