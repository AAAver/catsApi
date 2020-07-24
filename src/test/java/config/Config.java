package config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String PROP_PATH = "./cfg.properties";
    public static final String BASE_URL = getProperty("baseUrl");
    public static final String CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER_PATH = "./driver/chromedriver.exe";


    public static String getProperty(String key) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PROP_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);

    }

    public static void setProperty(String key, String value) {
        Properties prop = new Properties();
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PROP_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos = new FileOutputStream(PROP_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.put(key, value);
        try {
            prop.store(fos, "hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
