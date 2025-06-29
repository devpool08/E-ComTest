package org.ecom.base;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.ecom.utils.PropertiesUtil;
import org.ecom.utils.SingletonWebDriverFactoryUtils;
import org.ecom.utils.data.TestData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.util.Properties;

@Log4j2
@SuppressWarnings({"unused"})
public class BaseTest {

    protected Properties properties;
    protected FileReader reader;
    protected String browserName;
    protected SoftAssert softAssert;
    protected TestData testData;

    @SneakyThrows
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        try {
            if( browser == null || browser.isEmpty()) {
                log.error("Browser parameter is not provided or is empty. Defaulting to 'chrome'.");
                browser = "firefox";
            }
            testData=new TestData();
            SingletonWebDriverFactoryUtils.setThreadLocalDriver(browser);
            properties= PropertiesUtil.getProperties();
            softAssert = new SoftAssert();
            browserName = browser;
            log.info("setUp completed for {}", this.getClass().getName());
        } catch (Exception e) {
            log.error("Error during setup: {}", e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            SingletonWebDriverFactoryUtils.quitDriverAndRemove();
            log.info("tearDown completed for {}", this.getClass().getName());
        } catch (Exception e) {
            log.error("Error during tearDown: {}", e.getMessage());
            throw e;
        }
    }

}
