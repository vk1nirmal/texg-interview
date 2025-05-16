package com.saucedemo.pages;

import com.saucedemo.utils.ElementUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Page{

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(URL);
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isInventoryPageVisible() {
        return wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    public boolean isErrorMessageVisible() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getErrorMessageText() {
        try {
            return ElementUtils.getText(driver.findElement(errorMessage));
        } catch (Exception e) {
            return "";
        }
    }

    public String getSessionUsernameFromLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('session-username');");
    }

    public String getSessionUsernameFromCookie() {
        Cookie sessionCookie = driver.manage().getCookieNamed("session-username");
        return ElementUtils.getValue(sessionCookie);
    }
}
