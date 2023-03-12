package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import site.nomoreparties.stellarburgers.api.User;

public class AuthPage extends HeaderElement {
    private final By registrationButton = new By.ByXPath(".//a[text()='Зарегистрироваться']");
    private final By email = new By.ByName("name");
    private final By password = new By.ByName("Пароль");
    private final By enterHeader = new By.ByXPath(".//h2[text()='Вход']");
    private final By enterButton = new By.ByXPath(".//button[text()='Войти']");
    private final By recoverPassword = new By.ByXPath(".//a[text()='Восстановить пароль']");

    public AuthPage (WebDriver driver) {
        super(driver);
    }

    public WebElement findEnterHeader(){
       return driver.findElement(enterHeader);
    }

    @Step("Clicked on the button 'Восстановить пароль'")
    public void clickOnRegistrationButton() {
        clickOn(registrationButton);
    }

    @Step("Clicked on the button 'Зарегистрироваться'")
    public void clickOnRecoverPassword() {
        clickOn(recoverPassword);
    }

    @Step("User authorization")
    public void auth(User user) {
        clickOnAndEnter(email, user.getEmail());
        clickOnAndEnter(password, user.getPassword());

        clickOn(enterButton);
    }
}
