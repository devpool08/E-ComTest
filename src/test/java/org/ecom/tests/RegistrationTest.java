package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.pages.RegistrationPage;
import org.ecom.utils.exceptions.NavigationFailedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.ecom.utils.NavigationUtils.openPage;
import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal","all"})
public class RegistrationTest extends BaseTest {
    private RegistrationPage registrationPage;

    @Test
    public void openRegistrationPage() {
        try{
            openPage(getThreadLocalDriver(),properties.getProperty("register.url"));
            registrationPage = new RegistrationPage(getThreadLocalDriver());
            log.info("Registration page is opened successfully");
        }
        catch (Exception e) {
            log.error("Failed to open registration page: {}", e.getMessage());
            throw new NavigationFailedException("Failed to open registration page: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "openRegistrationPage")
    public void fillRegistrationForm() {
        try {
            registrationPage.enterFirstName(testData.getRandomName());
            registrationPage.enterLastName(testData.getRandomName());
            registrationPage.enterEmail(testData.getRandomEmail());
            registrationPage.enterAddress(testData.getRandomAddress());
            registrationPage.enterCity(testData.getRandomCity());
            for(String state: registrationPage.getStateOptions()) {
                log.info("State: {}", state);
            }
            int randomState= testData.getRandomNumber(0, registrationPage.getStateOptions().size() - 1);
            registrationPage.selectState(registrationPage.getStateOptions().get(randomState));
            registrationPage.enterPostcode(testData.getRandomPostcode());
            for(String country: registrationPage.getCountryOptions()) {
                log.info("Country: {}", country);
            }
            int randomCountry = testData.getRandomNumber(0, registrationPage.getCountryOptions().size() - 1);
            registrationPage.selectCountry(registrationPage.getCountryOptions().get(randomCountry));
            registrationPage.enterUsername(testData.getRandomUsername());
            String password = testData.getRandomPassword();
            registrationPage.enterPassword(password);
            registrationPage.enterConfirmPassword(password);
            registrationPage.agreeToTerms();
            log.info("Registration form filled successfully");
        } catch (Exception e) {
            log.error("Failed to fill registration form: {}", e.getMessage());
            throw new NavigationFailedException("Failed to fill registration form: " + e.getMessage());
        }
    }
    @Test(dependsOnMethods = "fillRegistrationForm")
    public void demoTestForFailing() {
        try {
            Assert.fail("Intentional failure for testing purposes");
            log.info("Form submit");
        } catch (Exception e) {
            log.error("Error during form submission: {}", e.getMessage());
            throw new NavigationFailedException("Error during form submission: " + e.getMessage());
        }
    }
    @Test(dependsOnMethods = "demoTestForFailing")
    public void demoTestForSkip() {
        try {
            Assert.assertTrue(true, "This test is intentionally skipped");
            log.info("Will Never Execute 1");
        } catch (Exception e) {
            log.error("Error during skip test: {}", e.getMessage());
            throw new NavigationFailedException("Error during skip test: " + e.getMessage());
        }
    }
    @Test(dependsOnMethods = "demoTestForFailing")
    public void demoTestForSkip2() {
        try {
            Assert.assertTrue(true, "This test is intentionally skipped");
            log.info("Will Never Execute 2");
        } catch (Exception e) {
            log.error("Error during skip test 2: {}", e.getMessage());
            throw new NavigationFailedException("Error during skip test 2: " + e.getMessage());
        }
    }
}
