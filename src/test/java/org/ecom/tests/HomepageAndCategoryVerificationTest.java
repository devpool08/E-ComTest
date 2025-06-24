package org.ecom.tests;

import lombok.extern.log4j.Log4j2;
import org.ecom.base.BaseTest;
import org.ecom.utils.NavigationUtils;
import org.ecom.utils.exceptions.NavigationFailedException;
import org.testng.annotations.Test;

import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;

@Log4j2
public class HomepageAndCategoryVerificationTest extends BaseTest {
    @Test
    public void openPage() {
        try{
            NavigationUtils.openPage(getThreadLocalDriver(),properties.getProperty("homepage.url"));
            log.info("Homepage is opened successfully");
        } catch (Exception e) {
            log.error("Failed to open homepage: {}", e.getMessage());
            throw new NavigationFailedException("Failed to open homepage"+e);
        }
    }
}
