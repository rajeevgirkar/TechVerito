package TechVerit.com.tech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class App 
{
	
	@Test
    public void OpenBrowser()
    {
    	System.setProperty("webdriver.gecko.driver","D:\\WORKSPACE\\seleniumpractise\\drivers\\geckodriver.exe");  
    	
    	WebDriver driver = new FirefoxDriver();
    	
    	driver.get("https://rajeev-girikar.herokuapp.com/admin/");
    	
    	driver.manage().window().maximize(); 
    	
    	
    }
}
