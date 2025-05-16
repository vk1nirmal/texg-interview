package com.saucedemo.tests;

import com.saucedemo.utils.ExcelDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import com.saucedemo.pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "excelLoginData", dataProviderClass = ExcelDataProvider.class)
    public void testLogin(String username, String password, boolean shouldLogin, String expectedError) {
        loginPage.open();
        loginPage.login(username, password);

        if (shouldLogin) {
            Assert.assertTrue(loginPage.isInventoryPageVisible(), "Login should succeed for: " + username);
            String sessionUsername = loginPage.getSessionUsernameFromCookie();
            Assert.assertEquals(sessionUsername, username, "Expected session username in Cookie.");
        } else {
            Assert.assertTrue(loginPage.isErrorMessageVisible(), "Error message should be visible for: " + username);
            Assert.assertEquals(loginPage.getErrorMessageText(), expectedError);
        }
    }

}