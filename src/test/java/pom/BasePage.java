package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class BasePage<T> {
	private static final String BASE_URL = "https://loanvantage360.com/LV317/";
	protected WebDriver webDriver;
	  public int TimeoutValue = 30;

	  public BasePage(WebDriver driver) {
	    webDriver = driver;
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, TimeoutValue), this);
	  }

	  public T loginWithDefaultUser() {
		  loginWithUser("test.user", "Temp123!", "IBS");
		  return (T) this;
	  }

	  public T loginWithUser(String userLogin , String userPassword, String bankName) {

		  webDriver.get(BASE_URL);
		  webDriver.findElement(By.id("inputBank")).sendKeys(bankName);
		  webDriver.findElement(By.id("continue")).click();
		  webDriver.findElement(By.id("inputLogin")).sendKeys(userLogin);
		  webDriver.findElement(By.id("inputPassword")).sendKeys(userPassword);
		  webDriver.findElement(By.id("btnLogin")).click();
	    return (T) this;
	  }
}
