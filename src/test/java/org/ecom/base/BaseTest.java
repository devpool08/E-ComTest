package org.ecom.base;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.ecom.utils.SingletonWebDriverFactoryUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.util.Properties;

@Log4j2
@SuppressWarnings({"unused"})
public class BaseTest {

    protected Faker faker;
    protected Properties properties;
    protected FileReader reader;
    protected String browserName;

    @SneakyThrows
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        try {
            faker= new Faker();
            SingletonWebDriverFactoryUtils.setThreadLocalDriver(browser);
            properties = new Properties();
            reader = new FileReader("./src/test/resources/config.properties");
            properties.load(reader);
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
