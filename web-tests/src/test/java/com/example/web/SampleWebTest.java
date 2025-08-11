package com.example.web;

import com.example.common.Config;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleWebTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() throws Exception {
        String remoteUrl = Config.get("SELENIUM_REMOTE_URL", "");
        if (!remoteUrl.isEmpty()) {
            AbstractDriverOptions<?> options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL(remoteUrl), options);
        } else {
            driver = new HtmlUnitDriver();
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void titleShouldBeRetrieved() {
        driver.get("data:text/html,<html><head><title>Test Page</title></head><body>Hello</body></html>");
        assertEquals("Test Page", driver.getTitle());
    }
}
