package stellarBurgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends HeaderElement {
    private final By name = new By.ByXPath(".//label[text()='Имя']/parent::div/input");
    private final By email = new By.ByXPath(".//label[text()='Email']/parent::div/input");
    private final By password = new By.ByName("Пароль");
    private final By registrationButton = new By.ByXPath(".//button[text()='Зарегистрироваться']");
    private final By passwordError = new By.ByXPath(".//p[text()='Некорректный пароль']");
    private final By enterButton = new By.ByClassName("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("User registration")
    public void registration(String newName, String newEmail, String newPassword) {
        clickOnAndEnter(name, newName);
        clickOnAndEnter(email, newEmail);
        clickOnAndEnter(password, newPassword);

        clickOn(registrationButton);
    }

    public WebElement findPasswordError() {
        return driver.findElement(passwordError);
    }

    @Step("Clicked on the button 'Войти'")
    public void clickOnEnterButton() {
        clickOn(enterButton);
    }
}
