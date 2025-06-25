package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.pages.HomePage;
import org.ecom.utils.data.TestData;
import org.ecom.utils.exceptions.NavigationFailedException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.ecom.utils.NavigationUtils.openPage;
import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal"})
public class HomepageAndCategoryVerificationTest extends BaseTest {
    private HomePage homePage;

    @Test
    public void openHomePage() {
        try{
            openPage(getThreadLocalDriver(),properties.getProperty("homepage.url"));
            assertEquals(getThreadLocalDriver().getTitle(),properties.getProperty("homepage.title"), "Homepage title does not match");
            homePage = new HomePage(getThreadLocalDriver());
            log.info("Homepage is opened successfully");
        } catch (Exception e) {
            log.error("Failed to open homepage: {}", e.getMessage());
            throw new NavigationFailedException("Failed to open homepage"+e);
        }
    }
    @Test(dependsOnMethods = "openHomePage")
    public void verifyCategoryPage() {
        softAssert = new SoftAssert();
        List<String> expectedCategoryNames = List.of("Home", "Apparel & accessories", "Makeup", "Skincare", "Fragrance","Men", "Haircare", "Books");
        List<String> actualCategoryNames = homePage.getCategoryMenuItems().stream()
                .map(WebElement::getText)
                .toList();
        try {
            for(int i=0;i<8;i++){
                String expectedCategoryName = expectedCategoryNames.get(i);
                String actualCategoryName = actualCategoryNames.get(i);
                softAssert.assertTrue(expectedCategoryName.replaceAll("\\s+", "").equalsIgnoreCase(actualCategoryName.replaceAll("\\s+", "")));
                System.out.println("Expected: " + expectedCategoryName + ", Actual: " + actualCategoryName);
            }
            softAssert.assertAll("Category names do not match");
            log.info("Category page is opened successfully");
        } catch (Exception e) {
            log.error("Failed to open category page: {}", e.getMessage());
            throw new NavigationFailedException("Failed to open category page" + e);
        }
    }
    @Test(dependsOnMethods = "verifyCategoryPage")
    public void navigateToRandomCategoryAndValidateMinimumNumberOfProduct() {
        try {
            List<WebElement> categories = homePage.getCategoryMenuItems();
            int randomIndex = TestData.getRandomNumber(0, categories.size() - 1);
            WebElement randomCategory = categories.get(randomIndex);
            String categoryName = randomCategory.getText();
            homePage.navigateToCategory(categoryName);
            List<WebElement> products = homePage.getProductItems();
            assertTrue(products.size() >= 3, "Expected at least 3 products in the category, but found: " + products.size());
            Thread.sleep(2000);
            log.info("Successfully navigated to category: {}", randomCategory);
        } catch (Exception e) {
            log.error("Failed to navigate to random category: {}", e.getMessage());
            throw new NavigationFailedException("Failed to navigate to random category" + e);
        }
    }
}
