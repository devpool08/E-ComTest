package org.ecom.base;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.ecom.utils.SingletonWebDriverFactoryUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;

@Log4j2
@SuppressWarnings({"unused","ResultOfMethodCallIgnored"})
public class BaseTest {

    protected Properties properties;
    protected FileReader reader;
    protected String browserName;

    @SneakyThrows
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        SingletonWebDriverFactoryUtils.setThreadLocalDriver(browser);
        properties = new Properties();
        reader = new FileReader("./src/test/resources/config.properties");
        properties.load(reader);
        browserName = browser;
        log.info("setUp completed for {}", this.getClass().getName());
    }
    public void openPage(String URL) {
        getThreadLocalDriver().get(URL);
        log.info("{} url is opened", URL);
    }

    @AfterClass
    public void tearDown() {
        SingletonWebDriverFactoryUtils.quitDriverAndRemove();
        log.info("{} test Completed Successfully", this.getClass().getName());
    }

}
