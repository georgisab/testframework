package pom;


import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class NewLoanRequstPopup3Page extends BasePage<NewLoanRequstPopup3Page> {
  public NewLoanRequstPopup3Page(WebDriver driver) {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  // Item selector
  @FindBy(xpath = "//label[contains(text(), 'Analyst Assigned')]/../div//input")
  private WebElement dateAnalystAssignedTxt;

  @FindBy(xpath = "//label[contains(text(), 'Loan Assistant')]/../div/div/span/span")
  private WebElement loanAssistantSelectBx;

  @FindBy(xpath = "//span[contains(text(), 'File Status')]/../../../div/div/span/span")
  private WebElement fileStatusSelectbx;

  @FindBy(xpath = "//label[contains(text(), 'Purpose')]/../div/div/span/span")
  private WebElement purposeSelectbx;

  @FindBy(xpath = "//label[contains(text(), 'Total Loan Amount')]/../div/div/span/span/input")
  private WebElement totalMoneyTxt;

  @FindBy(xpath = "//input[@id='new-money']/preceding-sibling::input")
  private WebElement newMoneyTxt;
  
  @FindBy(xpath = "//section[@id='new-loan-request-wizard']//button[ contains(., 'Next')]")
  private WebElement nextBtn;
    

  @FindBy(name = "LoanSecondaryOfficer")
  private WebElement loanSecondaryOfficerTxt;



  public NewLoanRequstPopup3Page selectPurpose(String selectValue) throws InterruptedException {
    purposeSelectbx.click();
    Thread.sleep(1500L);
    webDriver.findElement(By.xpath("//li/span[contains(text(), '" + selectValue + "')]")).click();
    return this;
  }

  public NewLoanRequstPopup3Page selectFileStatus(String selectValue) throws InterruptedException {
    Thread.sleep(500L);
    fileStatusSelectbx.click();
    Thread.sleep(1500L);
    webDriver.findElement(By.xpath("//li/span[contains(text(), '" + selectValue + "')]")).click();
    return this;
  }

  /**
   * 
   * @param dataValue MM/dd/yyyy
   * @return
   * @throws InterruptedException
   */
  public NewLoanRequstPopup3Page selectAnalystAssignedDate(String dateValue)
      throws InterruptedException {
    dateAnalystAssignedTxt.sendKeys(dateValue);
    Thread.sleep(1000L);
    dateAnalystAssignedTxt.click();
    dateAnalystAssignedTxt.sendKeys(dateValue);
    return this;
  }

  public NewLoanRequstPopup3Page addLoanSecondaryOfficer(String name) throws InterruptedException {
    loanSecondaryOfficerTxt.sendKeys(name);
    Thread.sleep(2000L);
    loanSecondaryOfficerTxt.sendKeys(Keys.DOWN);
    loanSecondaryOfficerTxt.sendKeys(Keys.ENTER);
    Thread.sleep(1000L);
    return this;
  }

  public List<String> getListLoanAssistant() throws InterruptedException {
    loanAssistantSelectBx.click();

    Thread.sleep(1500L);
    List<String> loanAssistant = new ArrayList<String>();

    webDriver.findElements(By.xpath("//ul[@id='ddlLoanAssistant_listbox']/li/span"))
        .forEach(element -> loanAssistant.add(element.getText()));
    loanAssistantSelectBx.click();
    Thread.sleep(1000L);
    return loanAssistant;
  }

  public NewLoanRequstPopup3Page selectLoanAssistant(String selectValue)
      throws InterruptedException {
    loanAssistantSelectBx.click();
    Thread.sleep(1500L);
    webDriver.findElement(By.xpath("//li/span[contains(text(), '" + selectValue + "')]")).click();
    Thread.sleep(500L);
    return this;
  }

  public NewLoanRequstPopup3Page addNewMoney(String selectValue) throws InterruptedException {

    webDriver.findElement(By.xpath("//input[@id='new-money']/..")).click();
    Thread.sleep(500L);
    webDriver.findElement(By.id("new-money")).sendKeys(selectValue);
    webDriver.findElement(By.id("new-money")).sendKeys(Keys.TAB);
    Thread.sleep(500L);

    return this;
  }

  public NewLoanRequstPopup3Page assertNewMoneyAreEqualToTheTotalMoney(String newMoney)
      throws InterruptedException {
    Thread.sleep(1000L);
    String totalMoney = totalMoneyTxt.getAttribute("title");
    totalMoney = totalMoney.replace("$", "").replace(",", "");
    assertThat(new BigDecimal(totalMoney)).isEqualByComparingTo(new BigDecimal(newMoney));
    return this;
  }
  public NewLoanRequstPopup4Page clickOnNext() throws InterruptedException {
    nextBtn.click();
    Thread.sleep(1500L);
    return new NewLoanRequstPopup4Page(webDriver);
  }

}
