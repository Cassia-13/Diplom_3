package stellarBurgersTest.user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.pages.AuthPage;
import site.nomoreparties.stellarburgers.pages.ConstructorPage;
import site.nomoreparties.stellarburgers.pages.RecoveryPasswordPage;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;
import stellarBurgersTest.DefaultTest;

import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class UserLoginTest extends DefaultTest {
    private final String path;

    public UserLoginTest(String browserName, String path) {
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
    @DisplayName("Login user on click button 'Войти в аккаунт'")
    public void loginByEnterAccountTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        WebElement expected = constructorPage.findPlaceOrderButton();
        Assert.assertTrue(expected.isDisplayed());
    }

    @Test
    @DisplayName("Login user on click button 'Личный Кабинет'")
    public void loginByPersonalAccountTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnPersonalAccount();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        WebElement expected = constructorPage.findPlaceOrderButton();
        Assert.assertTrue(expected.isDisplayed());
    }

    @Test
    @DisplayName("Login user on click button 'Войти' in form registration")
    public void loginFromRegistrationTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnPersonalAccount();

        AuthPage authPage = new AuthPage(driver);
        authPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickOnEnterButton();

        authPage.auth(user);

        WebElement expected = constructorPage.findPlaceOrderButton();
        Assert.assertTrue(expected.isDisplayed());
    }

    @Test
    @DisplayName("Login user on click button 'Войти' in page recovery password")
    public void loginFromRecoveryPageTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnPersonalAccount();

        AuthPage authPage = new AuthPage(driver);
        authPage.clickOnRecoverPassword();

        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.clickOnEnter();

        authPage.auth(user);

        WebElement expected = constructorPage.findPlaceOrderButton();
        Assert.assertTrue(expected.isDisplayed());
    }
}
