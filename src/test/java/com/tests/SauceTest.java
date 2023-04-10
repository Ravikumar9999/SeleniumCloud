package com.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceTest extends BaseTest{
	
	
	
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
