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
        FileInputStream fis;
        try {
            fis = new FileInputStream(PROP_PATH);
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        Properties prop = new Properties();
        FileOutputStream fos = null;
        FileInputStream fis;
        try {
            fis = new FileInputStream(PROP_PATH);
            prop.load(fis);
            fis.close();
            fos = new FileOutputStream(PROP_PATH);
            prop.put(key, value);
            prop.store(fos, "hello");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
