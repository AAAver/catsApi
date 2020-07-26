package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class Mail extends BasePage {

    @FindBy(css = "a[href*='0/#inbox']")
    private WebElement incomingMessages;

    @FindAll(@FindBy(css = "[role='link'] [data-legacy-last-non-draft-message-id]"))
    private List<WebElement> messageTitles;

    @FindBy(css = "[gh='cm']")
    private WebElement startNewMessage;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement messageTo;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement messageTheme;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement messageBody;

    @FindBy(css = "[role='group'] [role='button']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[@class='bsU']")
    private WebElement counter;

    Actions a = new Actions(driver);

    private Mail(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static Mail using(WebDriver driver) {
        return new Mail(driver);
    }

    public Mail waitUntilInboxListIsChanged() {
        wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("[role='link'] [data-legacy-last-non-draft-message-id]"),
                        messageTitles.size()));
        return this;
    }

    public int getMessageCountByTitle(String title) {
        click(incomingMessages);
        int count = 0;
        for (WebElement t : messageTitles) {
            if (t.getText().equals(title)) {
                count++;
            }
        }
        return count;
    }

    public Mail sendMessageToMyself(String to, String title, String message) {
        String t = counter.getText();
        click(startNewMessage);
        a.sendKeys(messageTo, to)
                .sendKeys(Keys.ENTER)
                .build().perform();
        a.click(messageTheme)
                .sendKeys(title)
                .sendKeys(Keys.ENTER)
                .build().perform();
        a.click(messageBody)
                .sendKeys(message)
                .build().perform();
        click(sendButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(counter)));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//*[@class='bsU']"), t)));
        return this;
    }

    public Mail refresh(){
        driver.navigate().refresh();
        return this;
    }
}
