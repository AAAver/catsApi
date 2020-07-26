package tests.smoke;

import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.mail.AuthorizationPage;
import pages.mail.MailPage;
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

        AuthorizationPage.using(driver)
                .setAccount(account)
                .submitAccount()
                .setPassword(password)
                .submitPassword();

        int initialNumber = MailPage.using(driver).getMessageCountByTitle(title);

        int afterNumber = MailPage.using(driver)
                .sendMessageToMyself(account, title, "")
                .refresh()
                .getMessageCountByTitle(title);

        Assert.assertEquals(afterNumber, initialNumber + 1);
    }


}
