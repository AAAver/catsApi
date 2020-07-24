package tests.smoke;

import config.Config;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.mail.Authorization;
import pages.mail.Mail;
import tests.BaseTest;

public class TestIncoming extends BaseTest {

    @BeforeMethod
    void setup(){
        setUpDriver();
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }

    @Test(description = "Отправка письма")
    public void sendMessageAndVerifyReceive() throws InterruptedException {
        String account  = Config.getProperty("account");
        String password = Config.getProperty("password");
        String title = Config.getProperty("title");

        Authorization auth = new Authorization(driver);
        auth.authorize(account, password);

        Mail mail = new Mail(driver);
        int initialNumber = mail.getMessageCountByTitle(title);

        mail.sendMessage(account, title, "");
        Thread.sleep(2000);
        driver.get(driver.getCurrentUrl());

        int afterNumber = mail.getMessageCountByTitle(title);
        Assert.assertEquals(initialNumber + 1,afterNumber);
    }


}
