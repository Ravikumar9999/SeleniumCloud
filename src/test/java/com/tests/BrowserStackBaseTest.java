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

public class BrowserStackBaseTest {
	
	
    WebDriver driver;
    
    public static final String userName =  "mohiniravikumar_AExlgH";
    public static final String  accessKey = "Y8qE9MXa14Ueqz4EcNCi";
    public static final String URL = "http://mohiniravikumar_aexlgh.browserstack.com";
    
   
	
	@Parameters({"browserName", "browserVersion", "os", "osVersion"})
	@BeforeMethod
	public void setUp(String browserName, String browserVersion, String os, String osVersion, Method name) {
		
		System.out.println("browser name is: " + browserName);
		//String methodName = name.getName();
		String methodName = name.GET.name();
		
		DesiredCapabilities decap = new DesiredCapabilities();
		
		decap.setCapability("os", os);
		decap.setCapability("osVersion", osVersion);
		decap.setCapability("browserVersion", browserVersion);
		decap.setCapability("name", methodName);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			decap.setCapability("browser", "Chrome");
		}
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			decap.setCapability("browser", "Firefox");
		}
		try {
			driver = new RemoteWebDriver(new URL (URL), decap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
