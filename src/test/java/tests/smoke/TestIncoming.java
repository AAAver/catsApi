package tests.smoke;

import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.mail.Authorization;
import pages.mail.Mail;
import tests.BaseTest;

public class TestIncoming extends BaseTest {

    @BeforeMethod
    void setup() {
        setUpDriver();
    }

//    @AfterMethod
//    void tearDown(){
//        driver.quit();
//    }

    @Test(description = "Отправка письма")
    public void sendMessageAndVerifyReceive() {
        String account = Config.getProperty("account");
        String password = Config.getProperty("password");
        String title = Config.getProperty("title");

        Authorization.using(driver)
                .setAccount(account)
                .submitAccount()
                .setPassword(password)
                .submitPassword();

        int initialNumber = Mail.using(driver).getMessageCountByTitle(title);

        int afterNumber = Mail.using(driver)
                .sendMessageToMyself(account, title, "")
                .refresh()
                .getMessageCountByTitle(title);

        Assert.assertEquals(afterNumber, initialNumber + 1);
    }


}
