package pages.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class AuthorizationPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement account;

    @FindBy(xpath = "//*[@id='identifierNext'] //button")
    private WebElement submitAccount;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='passwordNext'] //button")
    private WebElement submitPassword;

    private AuthorizationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static AuthorizationPage using(WebDriver driver){
        return new AuthorizationPage(driver);
    }

    public AuthorizationPage setAccount(String account){
        writeText(this.account, account);
        return this;
    }

    public AuthorizationPage submitAccount(){
        click(submitAccount);
        return this;
    }

    public AuthorizationPage setPassword(String password){
        writeText(this.password, password);
        return this;
    }

    public void submitPassword(){
        click(submitPassword);
    }

}
