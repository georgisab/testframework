package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewLoanRequstPopup5Page extends BasePage<NewLoanRequstPopup5Page> {
  public NewLoanRequstPopup5Page(WebDriver driver) {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  @FindBy(xpath = "//section[@id='new-loan-request-wizard']//button[ contains(., 'Save')]")
  private WebElement saveBtn;

  public LoanPage clickOnSave() throws InterruptedException {
    saveBtn.click();


    new WebDriverWait(webDriver, 60).until(ExpectedConditions
        .invisibilityOfElementLocated(By.xpath("//p[ contains(., 'Processing...')]")));
    Thread.sleep(5000L);
    return new LoanPage(webDriver);
  }
}
