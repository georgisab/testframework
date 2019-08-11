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

public class NewLoanRequstPopup2Page extends BasePage<NewLoanRequstPopup2Page> {
  public NewLoanRequstPopup2Page(WebDriver driver) {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  public NewLoanRequstPopup3Page selectFirstResultFromGrid() throws InterruptedException {
    Thread.sleep(4000L);
    webDriver
        .findElements(
            By.xpath("//th[contains(text(), 'Relationship Name')]/../../../tbody/tr/td/a"))
        .get(0).click();
    Thread.sleep(4000L);
    return new NewLoanRequstPopup3Page(webDriver);
  }
}
