package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(name = "user-name")
    private WebElement userName;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }
    public LoginPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }
    public ShopPage clickLoginButton() {
        this.loginButton.click();
        return new ShopPage(driver);
    }
    public ShopPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return clickLoginButton();
    }
}
