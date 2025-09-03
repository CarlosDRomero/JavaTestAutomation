package test.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.automation.pages.*;

import java.util.HashMap;
import java.util.Map;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    @Parameters({"URL"})
    @BeforeMethod
    public void beforeMethod(String URL) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // The following lines allow me to disable the chrome's "Leaked password" popup, which was stopping the tests execution
        // My solution is adapted from the following C# video: https://www.youtube.com/watch?v=eNsC5dbjPjw
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);


        driver = new ChromeDriver(options);

        driver.get(URL);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters({"userName", "password"})
    @Test
    public void login(String userName, String password) {
        // TODO: Add waits
//        loginPage.enterUserName(userName);
//        loginPage.enterPassword(password);
//        ShopPage shopPage = loginPage.clickLoginButton();
//        shopPage.addRandomProduct();
//        ShoppingCartPage shoppingCartPage = shopPage.clickShoppingCartButton();
//        CheckoutPage checkoutPage = shoppingCartPage.clickCheckoutButton();
//        checkoutPage.enterFirstName("Jose");
//        checkoutPage.enterLastName("Escamilla");
//        checkoutPage.enterPostalCode("12345");
//        checkoutPage.clickContinueButton();
//        CheckoutCompletePage checkoutCompletePage = checkoutPage.finishCheckout();
//        Assert.assertTrue(checkoutCompletePage.getCompleteHeaderText().contains("Thank you for your order!"));

        Assert.assertTrue(
            loginPage.login(userName, password)
                    .addRandomProduct()
                    .clickShoppingCartButton()
                    .clickCheckoutButton()
                    .enterFirstName("Jose")
                    .enterLastName("Escamilla")
                    .enterPostalCode("12345")
                    .clickContinueButton()
                    .finishCheckout()
                    .getCompleteHeaderText()
                    .contains("Thank you for your order!")

        );

    }

}
