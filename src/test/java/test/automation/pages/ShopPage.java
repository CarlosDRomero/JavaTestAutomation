package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShopPage extends BasePage {
    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartButton;
    @FindBy(className = "shopping_cart_badge")
    // I set it as a list because it allows me to check if the element is present on the page or not by checking if the list is empty.
    private List<WebElement> shoppingCartBadge;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;
    ShopPage(WebDriver driver) {
        super(driver);
    }
    private WebElement getShoppingCartBadge() {
        return shoppingCartBadge.isEmpty() ? null : shoppingCartBadge.get(0);
    }
    public int getShoppingCartCount() {
        WebElement shoppingCartBadge = getShoppingCartBadge();
        // If shopping cart badge is not on the page, there are 0 elements in the cart
        return shoppingCartBadge==null? 0 : Integer.parseInt(shoppingCartBadge.getText());
    }
    public int getInventorySize(){
        waitForVisibilityOf(shoppingCartButton);
        return addToCartButtons.size();
    }
    public ShopPage clickBurgerMenuButton(){
        waitForVisibilityOf(burgerMenuButton);
        burgerMenuButton.click();
        return this;
    }
    public LoginPage clickLogoutButton(){
        waitForVisibilityOf(logoutButton);
        logoutButton.click();
        return new LoginPage(driver);
    }
    public ShopPage clickAddToCartButton(int i) {
        addToCartButtons.get(i).click();
        return this;
    }
    public ShopPage addRandomProduct(){
        int i = (int) (Math.random() * addToCartButtons.size());
        return clickAddToCartButton(i);
    }


    public ShoppingCartPage clickShoppingCartButton() {
        waitForVisibilityOf(shoppingCartButton);
        shoppingCartButton.click();
        return new ShoppingCartPage(driver);
    }
    public LoginPage logout(){
        return clickBurgerMenuButton().clickLogoutButton();
    }


}
