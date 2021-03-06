package com.TechVerito.utilities;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Product_testcases  {

	public static WebDriver driver;
	
	
	
    // Before Test
	
	@BeforeTest

		public void Setup() {

		WebDriverManager.firefoxdriver().setup();

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rajeev-girikar.herokuapp.com/admin/");
		driver.manage().window().maximize();
		driver.findElement(By.id("admin_user_email")).sendKeys("admin@example.com");
		driver.findElement(By.id("admin_user_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
		
		
	}
 
	
	 
	
	// Verify product creation with Title , Sku  & Description
	
	@Test (priority = 1)
	
		public void CreateProductTest() {

		Products_Parameter Products = new Products_Parameter("MOBILE", "ABC123", "iPHONE"); 
		Products_methods Products_methods = new Products_methods();
		Products_methods.CreateProduct();
		Products_methods.fillproductdetails(Products);
		String successmsg = driver.findElement(By.className("flashes")).getText();
		
		Assert.assertEquals(successmsg, "Product was successfully created.");
		
     
	}
	
	// Verify product listing
	 
	@Test (priority = 2)
	  
	    public void listProductTest() {
	
		driver.findElement(By.id("products")).click();
		Assert.assertEquals(driver.findElement(By.className("resource_id_link")).isDisplayed(),true);
	}
	   
	
		
	// Verify product update with new Title , new Sku & new Description
	
	@Test(priority = 3)
	
	    public void updateProductTest() throws InterruptedException {

		Products_Parameter Products = new Products_Parameter("LAPTOP", "ABC456", "DELL");
		Products_methods Products_methods = new Products_methods();
		Products_methods.updateProduct();
		Products_methods.fillproductdetails(Products);
		String updatemsg = driver.findElement(By.className("flashes")).getText();
		Thread.sleep(5000);
		Assert.assertEquals(updatemsg, "Product was successfully updated.");

		
	}   
	
	// Verify product delete 
	
	
	
   	@Test(priority = 4)
	 
	    public void deleteProductTest() throws InterruptedException   {
		 
		Products_methods Products_methods = new Products_methods();
		Products_methods.deleteProduct();
		String deletemsg = driver.findElement(By.className("flashes")).getText();
		Thread.sleep(5000);
		Assert.assertEquals(deletemsg , "Product was successfully destroyed.");
       
	
	 }  
	
	// ALL TEST CASES ARE PASSED HENCE PRODUCT IS CREATED , LISTED , UPDATED & DELETED SUCCESSFULLY .
   	
   	
   	
	
	// checking error scenario Title , Sku & Description can't be blank & Null 
	
   	@Test (priority = 5) 
   	
   	    public void CreateProductTest2() throws InterruptedException {

		Products_Parameter Products = new Products_Parameter(" ", " ", " "); 
		Products_methods Products_methods = new Products_methods();
		Products_methods.CreateProduct();
		Products_methods.fillproductdetails(Products);
		String errormsg = driver.findElement(By.className("inline-errors")).getText();
		Thread.sleep(5000);
		Assert.assertEquals(errormsg, "can't be blank");
		
     
	}
   	    	
    // After Test
   	
	@AfterTest 
	
	  public void treardown ()
	  {
		driver.quit();
	  }
	   

}
