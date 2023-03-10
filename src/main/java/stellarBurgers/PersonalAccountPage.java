package stellarBurgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalAccountPage extends HeaderElement{
    private final By profile = new By.ByXPath(".//a[text()='Профиль']");
    private final By exitButton = new By.ByClassName("Account_button__14Yp3");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement findProfile() {
        return driver.findElement(profile);
    }

    @Step("Clicked on the button 'Выход'")
    public void clickOnExitButton() {
        clickOn(exitButton);
    }
}
