package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
protected WebDriver driver;
	

 public void setup()
	{
		
	System.setProperty("WebDriver.chrome.driver","C:\\Users\\Ravi\\eclipse-workspace\\Practice1\\Driver\\chromedriver.exe")	;
	driver=new ChromeDriver();
	}
	
	
   
 public void teardown()
	{
		
	driver.quit();
	}
	
	
	}


