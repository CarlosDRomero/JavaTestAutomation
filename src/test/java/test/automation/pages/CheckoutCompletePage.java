package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    private WebDriver driver;
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    @FindBy(id="back-to-products")
    private WebElement backToProducts;
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }
    public void clickBackToProducts() {
        backToProducts.click();
    }
}
