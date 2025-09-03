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

public class SauceTests {
    WebDriver driver;
    ShopPage shopPage;
    @Parameters({"URL", "userName", "password"})
    @BeforeMethod
    public void setup(String URL, String userName, String password) {
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
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        shopPage = loginPage.login(userName, password);

    }

    @Parameters({"firstName", "lastName", "postalCode"})
    @Test(testName = "Buy Flow")
    public void buyFlow(String firstName,  String lastName, String postalCode) {
        // TODO: Add waits
//        loginPage.enterUserName(userName);
//        loginPage.enterPassword(password);
//        ShopPage shopPage = loginPage.clickLoginButton();
//        shopPage.addRandomProduct();
//        ShoppingCartPage shoppingCartPage = shopPage.clickShoppingCartButton();
//        CheckoutPage checkoutPage = shoppingCartPage.clickCheckoutButton();
//        checkoutPage.enterFirstName(firstName);
//        checkoutPage.enterLastName(lastName);
//        checkoutPage.enterPostalCode(postalCode);
//        checkoutPage.clickContinueButton();
//        CheckoutCompletePage checkoutCompletePage = checkoutPage.finishCheckout();
//        Assert.assertTrue(checkoutCompletePage.getCompleteHeaderText().contains("Thank you for your order!"));

        CheckoutCompletePage checkoutCompletePage = shopPage.addRandomProduct()
                .clickShoppingCartButton()
                .clickCheckoutButton()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .clickContinueButton()
                .finishCheckout();

        Assert.assertTrue(
                checkoutCompletePage.getCompleteHeaderText().contains("Thank you for your order!")
        );

    }
    @Test(testName = "Remove from shopping cart")
    public void removeFromShoppingCart() {
        // First, I check if there are 6 elements on the shop
        Assert.assertEquals(shopPage.getInventorySize(), 6);
        shopPage.clickAddToCartButton(2)
                .clickAddToCartButton(4)
                .clickAddToCartButton(0);

        Assert.assertEquals(shopPage.getShoppingCartCount(), 3);
        // Then click the same buttons again to remove from cart
        shopPage.clickAddToCartButton(2)
                .clickAddToCartButton(4)
                .clickAddToCartButton(0);
        Assert.assertEquals(shopPage.getShoppingCartCount(), 0);
    }
    @Test(testName = "Logout is working")
    public void logout(){
        Assert.assertTrue(shopPage.logout().isInLoginPage());
    }
}
