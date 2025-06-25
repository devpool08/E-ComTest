package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.pages.CheckoutPage;
import org.ecom.pages.HomePage;
import org.openqa.selenium.WebDriver;
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
    private String expectedProductName1;
    private double expectedTotalPrice1;
    private String expectedProductPrice1;
    private String expectedProductName2;
    private double expectedTotalPrice2;
    private String expectedProductPrice2;
    private double expectedTotalPrice;
    private double actualTotalProductPrice1;
    private String actualProductName1;
    private double actualTotalProductPrice2;
    private String actualProductName2;
    private double actualTotalPrice;


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
    public void printProductDetails() {
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
        } catch (Exception e) {
            log.error("Failed to select product and add to cart: {}", e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "printProductDetails")
    public void selectProductAndAddToCart() {
        try {
            List<WebElement> products = homePage.getProductList();
            System.out.println("1---------------------");
            int product1 = testData.getRandomNumber(0, 8);
            System.out.println("2---------------------");
            WebElement randomProduct1 = products.get(product1);
            System.out.println("3---------------------");
            expectedProductName1 = homePage.getProductName(randomProduct1);
            System.out.println("4---------------------");
            expectedProductPrice1 = homePage.getProductPrice(randomProduct1);
            System.out.println("5---------------------");
            expectedTotalPrice1 = Double.parseDouble(expectedProductPrice1.replaceAll("[\\s\\u00A0\\u2000-\\u200B]+", "").replace("$", "").replaceAll("(?<=\\..*)\\.", ""));
            System.out.println("6---------------------" + expectedTotalPrice1);
            homePage.addToCart(randomProduct1);
            System.out.println("7---------------------");
            log.info("Random product selected and added to cart successfully: {} having price {}", expectedProductName1, expectedProductPrice1);
            System.out.println("8---------------------");
            int product2 = testData.getRandomNumber(7, products.size() - 2);
            System.out.println("9---------------------" + product2);
            WebElement randomProduct2 = products.get(product2);
            System.out.println("10---------------------");
            expectedProductName2 = homePage.getProductName(randomProduct2);
            System.out.println("11---------------------");
            expectedProductPrice2 = homePage.getProductPrice(randomProduct2);
            System.out.println("12---------------------");
            expectedTotalPrice2 = Double.parseDouble(expectedProductPrice2.replaceAll("[\\s\\u00A0\\u2000-\\u200B]+", "").replace("$", "").replaceAll("(?<=\\..*)\\.", ""));
            System.out.println("13---------------------" + expectedTotalPrice2);
            homePage.addToCart(randomProduct2);
            System.out.println("14---------------------");
            expectedTotalPrice = expectedTotalPrice1 + expectedTotalPrice2;
            System.out.println("15---------------------");
            log.info("Random product selected and added to cart successfully: {} having price {}", expectedProductName2, expectedProductPrice2);
            homePage.clickCartDropdown();
            System.out.println("16---------------------");
            log.info("Random product selected and added to cart successfully");
            log.info("Selected product and added to cart successfully");
        } catch (Exception e) {
            log.error("Failed to select product and add to cart: {}", e.getMessage());
            throw e;
        }
    }
    @Test(dependsOnMethods = "selectProductAndAddToCart")
    public void verifyCartDetails() {
        try {
            List<WebElement> productList = homePage.getCheckoutProductList();
            if (productList.isEmpty()) {
                log.error("No products found in the cart");
                throw new RuntimeException("No products found in the cart");
            }
            actualProductName1= homePage.getCheckoutProductName(productList.get(1));
            actualProductName2= homePage.getCheckoutProductName(productList.get(2));
            actualTotalProductPrice1 = Double.parseDouble(homePage.getCheckoutProductPrice(productList.get(1)).replaceAll("[\\s\\u00A0\\u2000-\\u200B]+", "").replace("$", "").replaceAll("(?<=\\..*)\\.", ""));
            actualTotalProductPrice2 = Double.parseDouble(homePage.getCheckoutProductPrice(productList.get(2)).replaceAll("[\\s\\u00A0\\u2000-\\u200B]+", "").replace("$", "").replaceAll("(?<=\\..*)\\.", ""));
            actualTotalPrice = actualTotalProductPrice1 + actualTotalProductPrice2;
            log.info("Product 1 name {}", actualProductName1);
            log.info("Product 1 price {}", actualTotalProductPrice1);
            log.info("Product 2 name {}", actualProductName2);
            log.info("Product 2 price {}", actualTotalProductPrice2);
            double roundOffActualTotalPrice = Math.round(actualTotalPrice * 100.0) / 100.0;
            double roundOffExpectedTotalPrice = Math.round(expectedTotalPrice * 100.0) / 100.0;
            softAssert.assertEquals(roundOffActualTotalPrice, roundOffExpectedTotalPrice, "Total price does not match");
            softAssert.assertAll();
            log.info("Product details verified successfully");
        } catch (Exception e) {
            log.error("Failed to verify cart details: {}", e.getMessage());
            throw e;
        }
    }
}
