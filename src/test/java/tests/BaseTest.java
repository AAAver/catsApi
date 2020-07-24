package tests;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public String baseUrl = Config.BASE_URL;

    public void setUpDriver() {
        System.setProperty(Config.CHROME_DRIVER, Config.CHROME_DRIVER_PATH);
        DesiredCapabilities dcap = new DesiredCapabilities();
        dcap.setCapability("pageLoadStrategy", "eager");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("start-maximized");
        opt.merge(dcap);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }
    public WebDriver getDriver() {
        return driver;
    }
}
