package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderElement extends TemplatePage{
    private final By logo = new By.ByClassName("AppHeader_header__logo__2D0X2");
    private final By constructor = new By.ByXPath(".//li[1]");
    private final By personalAccount = new By.ByXPath(".//p[text()='Личный Кабинет']");

    public HeaderElement(WebDriver driver) {
        super(driver);
    }

    public void clickOnLogo() {
        clickOn(logo);
    }

    public void clickOnConstructor() {
        clickOn(constructor);
    }

    public void clickOnPersonalAccount() {
        clickOn(personalAccount);
    }
}
