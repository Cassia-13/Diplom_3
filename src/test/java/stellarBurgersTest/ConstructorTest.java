package stellarBurgersTest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.pages.AuthPage;
import site.nomoreparties.stellarburgers.pages.ConstructorPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ConstructorTest extends DefaultTest {
    private final String path;

    public ConstructorTest(String browserName, String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "Проверка в {0}")
    public static Object[][] getPath() {
        return new Object[][]{
                {"Yandex browser", "C:\\Users\\isupo\\Downloads\\yandexdriver-23.1.0.2864-win64\\yandexdriver.exe"},
                {"Chrome browser", "D:\\KateProject\\WebDriver\\bin\\chromedriver.exe"},
        };
    }

    @Before
    public void createUser() {
        userAPI.create(user);
    }

    @Test
    @DisplayName("Switching to the 'Булки' tab in the constructor")
    public void clickOnBunTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        constructorPage.clickOnIngredientsTab();
        constructorPage.clickOnBunTab();

        String expected = "Булки";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }

    @Test
    @DisplayName("Switching to the 'Соусы' tab in the constructor")
    public void clickOnSouseTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        constructorPage.clickOnSouseTab();

        String expected = "Соусы";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }

    @Test
    @DisplayName("Switching to the 'Начинки' tab in the constructor")
    public void clickOnIngredientTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        constructorPage.clickOnIngredientsTab();

        String expected = "Начинки";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }
}
