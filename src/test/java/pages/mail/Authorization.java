package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Authorization extends BasePage {

    public Authorization(WebDriver driver) {
        super(driver);
    }
    private By loginField = By.xpath("//input[@type='email']");
    private By proceedLoginBtn = By.xpath("//*[@id='identifierNext'] //button");
    private By passwordField = By.xpath("//input[@type='password']");
    private By proceedPasswordBtn = By.xpath("//*[@id='passwordNext'] //button");

    public void authorize(String login, String password){
        writeText(loginField, login);
        click(proceedLoginBtn);
        writeText(passwordField, password);
        click(proceedPasswordBtn);
    }
}
