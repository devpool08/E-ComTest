package org.ecom.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties;
    @SneakyThrows
    public static  Properties getProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
