package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class TemplatePage {

    protected WebDriver driver;

    public TemplatePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickOn(By locator) { //клик
        driver.findElement(locator).click();
    }

    protected void clickOnAndEnter(By input, String newData) { //клик и ввести
        clickOn(input);
        driver.findElement(input).sendKeys(newData);
    }

    protected void scrollToElement(By locator) { //скролл
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
