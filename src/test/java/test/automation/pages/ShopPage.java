package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopPage {
    WebDriver driver;
    @FindBy(className = "btn_inventory")
    List<WebElement> addToCartButtons;
    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartButton;
    @FindBy(className="shopping_cart_badge")
    // I set it as a list because it allows me to check if the element is present on the page or not by checking if the list is empty.
    List<WebElement> shoppingCartBadge;

    ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        return addToCartButtons.size();
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
        shoppingCartButton.click();
        return new ShoppingCartPage(driver);
    }


}
