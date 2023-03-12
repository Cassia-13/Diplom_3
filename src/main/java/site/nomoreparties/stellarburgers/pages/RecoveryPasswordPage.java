package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage extends HeaderElement{
    private final By enterButton = new By.ByClassName("Auth_link__1fOlj");

    public RecoveryPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Clicked on the button 'Войти'")
    public void clickOnEnter() {
        clickOn(enterButton);
    }
}
