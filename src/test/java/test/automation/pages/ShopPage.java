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
    ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShopPage clickAddToCartButton(int i) {
        addToCartButtons.get(i).click();

        return this;
    }
    public ShopPage addRandomProduct(){
        int i = (int) (Math.random() * addToCartButtons.size());
        clickAddToCartButton(i);

        return this;
    }

    public ShoppingCartPage clickShoppingCartButton() {
        shoppingCartButton.click();
        return new ShoppingCartPage(driver);
    }


}
