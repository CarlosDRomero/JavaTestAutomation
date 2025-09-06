package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(className = "login_container")
    private List<WebElement> loginContainer;
    @FindBy(name = "user-name")
    private WebElement userName;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public boolean isInLoginPage() {
        return !loginContainer.isEmpty();
    }
    public LoginPage enterUserName(String userName) {
        waitForVisibilityOf(this.userName);
        this.userName.sendKeys(userName);
        return this;
    }
    public LoginPage enterPassword(String password) {
        waitForVisibilityOf(this.password);
        this.password.sendKeys(password);
        return this;
    }
    public ShopPage clickLoginButton() {
        waitForVisibilityOf(loginButton);
        this.loginButton.click();
        return new ShopPage(driver);
    }
    public ShopPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return clickLoginButton();
    }
}
