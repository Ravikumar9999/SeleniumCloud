package com.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.http.Method;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	
	
	@Parameters({"browser", "platform", "version"})
	@BeforeMethod
	public void setUp(String browserName, String platformName, String versionName, Method name) {
		
		System.out.println("browser name is: " + browserName);
		//String methodName = name.getName();
		String methodName = name.GET.name();
		
		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("build", methodName);
		sauceOpts.setCapability("seleniumVersion", "4.8.3");
		sauceOpts.setCapability("username", "oauth-mohini.ravikumar-b35ea");
		sauceOpts.setCapability("accessKey", "dbde3336-575f-455a-baa2-84332f5aa86f");
		sauceOpts.setCapability("tags", "w3c-chrome-tests");
		
		DesiredCapabilities decap = new DesiredCapabilities();
		decap.setCapability("sauce:options", sauceOpts);
		decap.setCapability("browserVersion", versionName);
		decap.setCapability("platformName", platformName);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			decap.setCapability("browserName", "chrome");
		}
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			decap.setCapability("browserName", "firefox");
		}
		// https://oauth-mohini.ravikumar-b35ea:dbde3336-575f-455a-baa2-84332f5aa86f@ondemand.eu-central-1.saucelabs.com:443/wd/hub
		try {
			driver = new RemoteWebDriver(new URL ("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), decap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		this.driver.quit();
	}

}
