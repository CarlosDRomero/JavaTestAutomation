package test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage{
    protected final WebDriver driver;
    private static final int TIMEOUT = 10;
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void waitForExpectedConditions(ExpectedCondition<WebElement> condition){
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(condition);
    }
}
