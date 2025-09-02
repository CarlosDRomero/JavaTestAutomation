package test.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    @FindBy(id = "first-name")
    WebElement firstName;
    @FindBy(id = "last-name")
    WebElement lastName;
    @FindBy(id = "postal-code")
    WebElement postalCode;
    @FindBy(id = "continue")
    WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }
    public void enterPostalCode(String postalCode) {
        this.postalCode.sendKeys(postalCode);
    }
    public void clickContinueButton() {
        continueButton.click();
    }
    public CheckoutCompletePage finishCheckout() {
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
