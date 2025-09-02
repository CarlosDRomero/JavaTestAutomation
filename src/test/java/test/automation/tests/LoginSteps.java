package test.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.automation.pages.LoginPage;
import test.automation.pages.ShopPage;
import test.automation.pages.ShoppingCartPage;

import java.util.HashMap;
import java.util.Map;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Con estas líneas puedo desactivar un popup que me salía en chrome de que encontraba la contraseña del login como una contraseña insegura
        // Esta solución es una adaptación de lo encontrado en este vídeo con C#: https://www.youtube.com/watch?v=eNsC5dbjPjw
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);


        driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }
    @Parameters({"userName", "password"})
    @Test
    public void login(String userName, String password) {
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        ShopPage shopPage = loginPage.clickLoginButton();
        shopPage.addRandomProduct();
    }

}
