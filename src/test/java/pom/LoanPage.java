package pom;

import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoanPage extends BasePage<LoanPage> {
  public LoanPage(WebDriver driver) {
    super(driver);

    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
  }

  @FindBy(xpath = "//label[contains(text(), 'Purpose')]/../div/span")
  private WebElement purposeSelectbx;

  @FindBy(xpath = "//label/span/span[contains(text(), 'File Status')]/../../../div/span")
  private WebElement fileStatusSelectbx;

  public LoanPage assertLoadPuropose(String purpose) {
    assertThat(purposeSelectbx.getText()).isEqualTo(purpose);
    return this;
  }

  public LoanPage assertFileStatus(String fileStatus) {
     assertThat(fileStatusSelectbx.getText()).isEqualTo(fileStatus);
    return this;
  }
}
