package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage<HomePage> {
  public HomePage(WebDriver driver) {
    super(driver);

    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  // Menu Item selector
  @FindBy(xpath = "//span[@class='k-link' and text()='New']")
  private WebElement menuNewBtn;


  public NewLoanRequstPopupPage clickOnMenuNewLoadRequest() throws InterruptedException {
    menuNewBtn.click();
    Thread.sleep(500L);
    new WebDriverWait(webDriver, 20)
        .until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='https://loanvantage360.com/LV317/Portal/#/NewLoanRequest/']")))
        .click();
    return new NewLoanRequstPopupPage(webDriver);
    
  }
}
