package org.ecom.utils;

import lombok.extern.log4j.Log4j2;
import org.ecom.utils.exceptions.ScreenshotFailedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.ecom.utils.SingletonWebDriverFactoryUtils.getThreadLocalDriver;

@Log4j2
public class ScreenshotUtil {
    public static String captureScreenshot(String name) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot screenshot = (TakesScreenshot) getThreadLocalDriver();
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        boolean b = sourceFile.renameTo(targetFile);
        if (!b) {
            log.error("Failed to save screenshot at {}", targetFilePath);
            throw new ScreenshotFailedException("Failed to save screenshot at " + targetFilePath);
        }
        return targetFilePath;
    }
}
