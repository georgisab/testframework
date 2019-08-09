package vanguard.testframework.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class ChromeDriverManager extends DriverManager {

	@Override
	protected void createWebDriver() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("chromedriver.exe"); 
		try {
			System.setProperty("webdriver.chrome.driver", url.getPath());
				//new File(url.getPath()).getCanonicalPath().replaceAll("%20", " "));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		this.driver = new ChromeDriver(); 

	}

}
