package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserstackTest {
	
	
public WebDriver driver;
    
    public static final String USERNAME =  "mohiniravikumar_AExlgH";
    public static final String  AUTOMATE_KEY = "Y8qE9MXa14Ueqz4EcNCi";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    
    
    
    @BeforeMethod
    public void setUp() throws MalformedURLException {
    	DesiredCapabilities caps = new DesiredCapabilities();
    	
    	caps.setCapability("browserName", "Chrome");
    	caps.setCapability("browserVersion", "110");
    	caps.setCapability("os", "Windows");
    	caps.setCapability("osVersion", "10");
    	
    	URL browserStackUrl = new URL(URL);
    	
    	driver = new RemoteWebDriver (browserStackUrl, caps);
    	
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    }
    
    @BeforeClass
    public void doLogin() {
		driver.get("https://www.saucedemo.com/inventory.html");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
	}
    
    

	@Test(priority = 1)
	public void checkInventoryItemTest() {
		doLogin();
		Assert.assertTrue(driver.findElements(By.className("inventory_item_name")).size() == 6);
	}
	
	@Test(priority = 2)
	public void addToCartButtonsTest() {
		doLogin();
		Assert.assertTrue(driver.findElements(By.xpath("//button[text()='Add to cart']")).size() == 6);
	}
	
	
	

}
