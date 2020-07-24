package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

import java.util.List;

public class Mail extends BasePage {
    public Mail(WebDriver driver) {
        super(driver);
    }

    private final By incomingMessages = By.cssSelector("a[href*='0/#inbox']");
    private final By messageTitle = By.cssSelector("[role='link'] [data-legacy-last-non-draft-message-id]");
    private final By startNewMessage = By.cssSelector("[gh='cm']");
    private final By messageTo = By.xpath("//textarea[@name='to']");
    private final By messageTheme = By.xpath("//input[@name='subjectbox']");
    private final By messageBody = By.xpath("//div[@role='textbox']");
    private final By sendButton = By.cssSelector("[role='group'] [role='button']");


    public int getMessageCountByTitle(String title){
        click(incomingMessages);
        List<WebElement> titles = getElementList(messageTitle);
        int count = 0;
        for(WebElement t : titles){
            if (getText(t).equals(title)){
                count++;
            }
        }
        return count;
    }

    public void sendMessage(String to, String title, String message){
        click(startNewMessage);
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(messageTo),to)
                .sendKeys(Keys.ENTER)
                .build().perform();
        a.click(driver.findElement(messageTheme))
                .sendKeys(title)
                .sendKeys(Keys.ENTER)
                .build().perform();
        a.click(driver.findElement(messageBody))
                .sendKeys(message)
                .build().perform();
        click(sendButton);
    }
}
