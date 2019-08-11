package vanguard.testframework.it;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pom.HomePage;
import pom.NewLoanRequstPopup3Page;
import vanguard.testframework.utils.ChromeDriverManager;


public class AppTests {

  WebDriver driver;
  HomePage homePage;

  @After
  public void after() {
    driver.close();
  }

  @Test
  public void testTask() throws Exception {
    String name = "Haselhoff";
    String money = "10000";
    String purpose = "(ARNC) Annual Review (NC)";
    String fileStatus = "Committed";
    driver = new ChromeDriverManager().getWebDriver();
    new HomePage(driver).loginWithDefaultUser()
        .clickOnMenuNewLoadRequest()
        .enterNameOfBusiness(name)
        .selectSearchResultFromGrid(name, 1)
        .selectFirstResultFromGrid()
        .selectPurpose(purpose)
        .selectFileStatus(fileStatus)
        .selectAnalystAssignedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("M/dd/yyyy")))
        .addLoanSecondaryOfficer("Test");
               NewLoanRequstPopup3Page loanRequestPage3 =  new NewLoanRequstPopup3Page(driver);
                   loanRequestPage3
         .selectLoanAssistant(loanRequestPage3.getListLoanAssistant().get(1))
         .addNewMoney(money)
         .assertNewMoneyAreEqualToTheTotalMoney(money)
         .clickOnNext()
         .clickOnNext()
         .clickOnSave()
         .assertFileStatus(fileStatus)
         .assertLoadPuropose(purpose);
  }
}
