package org.amazon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader(String filePath) {
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getMobilePhoneNumber() {
        return properties.getProperty("mobilePhoneNumber");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getStreetName() {
        return properties.getProperty("streetName");
    }

    public String getCityArea() {
        return properties.getProperty("cityArea");
    }

    public String getBuildingNumber() {
        return properties.getProperty("buildingNumber");
    }

    public String getDistrict() {
        return properties.getProperty("district");
    }

    public String getAddressPhone() {
        return properties.getProperty("addressPhone");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public String getFirstName() {
        return properties.getProperty("firstName");
    }
    public String getLastName() {
        return properties.getProperty("lastName");
    }
}
