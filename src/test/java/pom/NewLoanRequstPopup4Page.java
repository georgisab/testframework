package pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class NewLoanRequstPopup4Page extends BasePage<NewLoanRequstPopup4Page> {
  public NewLoanRequstPopup4Page(WebDriver driver) {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }
  
  @FindBy(xpath = "//section[@id='new-loan-request-wizard']//button[ contains(., 'Next')]")
  private WebElement nextBtn;

  public NewLoanRequstPopup5Page clickOnNext() throws InterruptedException {
    nextBtn.click();
    Thread.sleep(1500L);
    return new NewLoanRequstPopup5Page(webDriver);
  }
}
