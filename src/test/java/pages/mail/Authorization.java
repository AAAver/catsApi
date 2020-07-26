package pages.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class Authorization extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement account;

    @FindBy(xpath = "//*[@id='identifierNext'] //button")
    private WebElement submitAccount;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='passwordNext'] //button")
    private WebElement submitPassword;

    private Authorization(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static Authorization using(WebDriver driver){
        return new Authorization(driver);
    }

    public Authorization setAccount(String account){
        writeText(this.account, account);
        return this;
    }

    public Authorization submitAccount(){
        click(submitAccount);
        return this;
    }

    public Authorization setPassword(String password){
        writeText(this.password, password);
        return this;
    }

    public void submitPassword(){
        click(submitPassword);
    }

}
