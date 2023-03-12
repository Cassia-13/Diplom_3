package stellarBurgersTest.personalAccount;

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
import site.nomoreparties.stellarburgers.pages.PersonalAccountPage;
import stellarBurgersTest.DefaultTest;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TransitionToConstructorTest extends DefaultTest {
    private final String path;

    public TransitionToConstructorTest(String browserName, String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "Проверка в {0}")
    public static Object[][] getPath() {
        return new Object[][]{{"Yandex browser", "C:\\Users\\isupo\\Downloads\\yandexdriver-23.1.0.2864-win64\\yandexdriver.exe"}, {"Chrome browser", "D:\\KateProject\\WebDriver\\bin\\chromedriver.exe"},};
    }

    @Before
    public void createUser() {
        userAPI.create(user);

    }

    @Test
    @DisplayName("Click on the logo button to go to the constructor from your personal account")
    public void transitionByLogoTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        constructorPage.clickOnPersonalAccount();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickOnLogo();

        Assert.assertEquals(BASEURL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Click on the button 'Конструктор' to go to the constructor from your personal account")
    public void transitionByConstructorTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(user);

        constructorPage.clickOnPersonalAccount();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickOnConstructor();

        Assert.assertEquals(BASEURL, driver.getCurrentUrl());
    }
}
