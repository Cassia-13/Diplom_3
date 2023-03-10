package stellarBurgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage extends HeaderElement {
    private final By loginButton = new By.ByXPath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = new By.ByXPath(".//button[text()='Оформить заказ']");

    private final By bunTab = new By.ByXPath(".//span[text()='Булки']");
    private final By souseTab = new By.ByXPath(".//span[text()='Соусы']");
    private final By ingredientsTab = new By.ByXPath(".//span[text()='Начинки']");

    private final By selectedTab = new By.ByXPath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Clicked on the button 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        clickOn(loginButton);
    }

    public WebElement findPlaceOrderButton() {
        return driver.findElement(placeOrderButton);
    }

    @Step("Clicked on the tab 'Булки'")
    public void clickOnBunTab() {
        clickOn(bunTab);
    }

    @Step("Clicked on the tab 'Соусы'")
    public void clickOnSouseTab() {
        clickOn(souseTab);
    }

    @Step("Clicked on the tab 'Начинки'")
    public void clickOnIngredientsTab() {
        clickOn(ingredientsTab);
    }

    public WebElement findSelectedButton() {
        return driver.findElement(selectedTab);
    }
}
