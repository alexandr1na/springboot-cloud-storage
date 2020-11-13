package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean isPageDisplayed() {
        return isElementDisplayed(By.id("logoutButton"))
                && isElementDisplayed(By.id("nav-files-tab"))
                && isElementDisplayed(By.id("nav-notes-tab"))
                && isElementDisplayed(By.id("nav-credentials-tab"));
    }

    @Override
    WebDriver getWebDriver() {
        return webDriver;
    }
}
