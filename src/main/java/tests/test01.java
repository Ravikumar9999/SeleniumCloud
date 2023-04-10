package tests;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test01 {
	public WebDriver driver;


	public static final String USERNAME =  "mohiniravikumar_AExlgH";
    public static final String  AUTOMATE_KEY = "Y8qE9MXa14Ueqz4EcNCi";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	
	@Test
	public void openSTM() throws Exception {
                // To execute scripts through remote server or grid on mulitple browsers, we need to set capabilities of platform, version etc., to run the scripts
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setPlatform(Platform.WINDOWS);
		capability.setBrowserName("chrome");
		capability.setVersion("110");
		capability.setCapability("browserstack.debug", "true");
		
		// Creatng URL object
		URL browserStackUrl = new URL(URL);
		// Create object of driver. We execute scripts remotely. So we use RemoteWebDriver
		//There are many constructors to remotewebdriver
                //To pass URL object and Capabilities object, use the below mentioned constructor
                //RemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities)
		driver = new RemoteWebDriver (browserStackUrl, capability);
		
		
		
		// to open url
		driver.get("https://www.saucedemo.com/inventory.html");
		driver.manage().window().maximize();
		
		//Implicit wait for 30 seconds
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		List<WebElement> itemSize = driver.findElements(By.className("inventory_item_name"));
		System.out.println(itemSize.size());
		
		List<WebElement> cartButton = driver.findElements(By.xpath("//button[text()='Add to cart']"));
		System.out.println(cartButton.size());
		driver.quit();
	}
}
