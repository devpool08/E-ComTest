package org.ecom.utils;

import org.ecom.utils.exceptions.InvalidWebDriverException;
import org.ecom.utils.factories.ChromeDriverFactory;
import org.ecom.utils.factories.EdgeDriverFactory;
import org.ecom.utils.factories.FirefoxDriverFactory;
import org.openqa.selenium.WebDriver;

public class SingletonWebDriverFactoryUtils {
    private static SingletonWebDriverFactoryUtils instance;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getThreadLocalDriver() {
        return threadLocalDriver.get();
    }

    public static void setThreadLocalDriver(String browser) {
        threadLocalDriver.set(getInstance().getDriver(browser));
    }

    public static void quitDriverAndRemove() {
        getThreadLocalDriver().quit();
        threadLocalDriver.remove();
    }

    private SingletonWebDriverFactoryUtils() {}

    private synchronized static SingletonWebDriverFactoryUtils getInstance() {
        if (instance == null){
            return instance = new SingletonWebDriverFactoryUtils();
        }
        return instance;
    }

    private WebDriver getDriver(String browser){
        return switch (browser.toLowerCase()){
            case "chrome" -> new ChromeDriverFactory().createDriver();
            case "edge" -> new EdgeDriverFactory().createDriver();
            case "firefox" -> new FirefoxDriverFactory().createDriver();
            default -> throw new InvalidWebDriverException("Browser is not available");
        };
    }
}
