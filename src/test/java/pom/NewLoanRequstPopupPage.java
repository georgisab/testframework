package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewLoanRequstPopupPage extends BasePage<NewLoanRequstPopupPage> {
  public NewLoanRequstPopupPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  // Item selector
  @FindBy(xpath = "//input[@name='Name' and not(@id)]")
  private WebElement nameLoanTxt;


  public NewLoanRequstPopupPage enterNameOfBusiness(String name) throws InterruptedException {
    nameLoanTxt.clear();
    nameLoanTxt.sendKeys(name);
    nameLoanTxt.sendKeys(Keys.ENTER);
    return this;
  }

  /**
   * 
   * @param name Name from grip
   * @param resNum result number start form 1
   * @return
   * @throws InterruptedException 
   */
  public NewLoanRequstPopup2Page selectSearchResultFromGrid(String name, int resNum) throws InterruptedException {
    Thread.sleep(4000L);
    webDriver
        .findElements(By.xpath("//div[@id='search-result-grid']//td[contains(.,'" + name + "')]/a"))
        .get(resNum - 1).click();
    return new NewLoanRequstPopup2Page(webDriver);

  }
  
  public NewLoanRequstPopup2Page selectFirstResultFromGrid() {
    new WebDriverWait(webDriver, 20)
        .until(ExpectedConditions.visibilityOfAllElements(
            webDriver.findElements(By.xpath("//div[@id='search-result-grid']//td/a"))))
        .get(0).click();;

    return new NewLoanRequstPopup2Page(webDriver);
  }

}
