package vanguard.testframework.it;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import pom.HomePage;
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
		
		driver = new ChromeDriverManager().getWebDriver();
		homePage = new HomePage(driver);
		homePage.loginWithDefaultUser();
	}
}
