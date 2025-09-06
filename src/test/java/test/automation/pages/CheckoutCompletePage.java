package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    @FindBy(id="back-to-products")
    private WebElement backToProducts;
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    public String getCompleteHeaderText() {
        waitForVisibilityOf(completeHeader);
        return completeHeader.getText();
    }
    public ShopPage clickBackToProducts() {
        waitForVisibilityOf(backToProducts);
        backToProducts.click();
        return new ShopPage(driver);
    }
}
