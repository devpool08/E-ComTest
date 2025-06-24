package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.pages.HomePage;
import org.ecom.utils.NavigationUtils;
import org.ecom.utils.exceptions.NavigationFailedException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;
import static org.testng.Assert.assertEquals;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal"})
public class HomepageAndCategoryVerificationTest extends BaseTest {
    private HomePage homePage;
    private SoftAssert softAssert;

    @Test
    public void openPage() {
        try{
            NavigationUtils.openPage(getThreadLocalDriver(),properties.getProperty("homepage.url"));
            assertEquals(getThreadLocalDriver().getTitle(),properties.getProperty("homepage.title"), "Homepage title does not match");
            homePage = new HomePage(getThreadLocalDriver());
            log.info("Homepage is opened successfully");
        } catch (Exception e) {
            log.error("Failed to open homepage: {}", e.getMessage());
            throw new NavigationFailedException("Failed to open homepage"+e);
        }
    }
    @Test(dependsOnMethods = "openPage")
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
}
