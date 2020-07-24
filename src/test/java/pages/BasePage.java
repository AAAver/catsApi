package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By by) {
        waitVisibility(by);
        driver.findElement(by).click();

    }

    public void click(WebElement element) {
        waitVisibility(element);
        element.click();
    }

    public void clickJS(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
    }

    public void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void writeText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }

    public List<WebElement> getElementList(By elementLocator) {
        waitVisibilityMultipleElements(elementLocator);
        return driver.findElements(elementLocator);
    }

    public String getText(By by) {
        waitVisibility(by);
        return driver.findElement(by).getText();
    }

    public String getText(WebElement element) {
        waitVisibility(element);
        return element.getText();
    }

    public void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitVisibilityMultipleElements(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception TimeoutException) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        }
    }

    public String getAttribute(By by, String attribute) {
        try {
            waitVisibility(by);
            return driver.findElement(by).getAttribute(attribute);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getAttribute(WebElement element, String attribute) {
        try {
            waitVisibility(element);
            return element.getAttribute(attribute);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void clearField(By by) {
        driver.findElement(by).clear();
    }

    public void clearField(WebElement element) {
        element.clear();
    }

    public boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void setDate(By by, String date) {
        waitVisibility(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + date + "')", driver.findElement(by));
        driver.findElement(by).sendKeys(Keys.chord(Keys.ENTER));
    }

}
