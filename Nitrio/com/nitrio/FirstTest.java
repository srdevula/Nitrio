package com.nitrio;

import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
	
	public static WebDriver driver;
	public static String name;
	public static Alert a;
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\paypal\\drivers\\chromedriver.exe");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("user-data-dir=C:/Users/devulsr/AppData/Local/Google/Chrome/User Data");
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		
		Thread.sleep(3000);
    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
    driver.close();
   driver.switchTo().window(tabs2.get(0));
		driver.navigate().to("chrome-extension://dniinonmliiclhbbkamokcmabdhfjioh/pages/dashboard.html#/recommendations");
		
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Recommendations')]")));
		
	driver.findElement(By.xpath("//a[contains(text(),'Recommendations')]")).click();
	
	driver.findElement(By.xpath("//a[contains(text(),'New Recommendation')]")).click();
	
	//explicit wait

	WebDriverWait w1 = new WebDriverWait(driver,30);
	w1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Subject')]")));
	
	//subject
	driver.findElement(By.xpath("(//div[@class='nitrio-modal-body']//div[@class='nitrio-modal-margin'])[1]//input")).sendKeys("Srinivas");
	
	//Recommendation text
	driver.findElement(By.xpath("(//div[@class='nitrio-modal-body']//div[@class='nitrio-modal-margin'])[2]//textarea")).sendKeys("D");
	
	
	//topic
	driver.findElement(By.xpath("(//div[@class='nitrio-modal-body']//div[@class='nitrio-modal-margin'])[3]//input")).sendKeys("Customer is interested in A/B testing of their product");
	
	WebDriverWait w2 = new WebDriverWait(driver,30);
	w2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='nitrio-modal-body']//div[@class='nitrio-modal-margin'])[3]//div[contains(@class,'select-topic-autocomplete')]")));
	
	//select the value from autocomplete
	driver.findElement(By.xpath("(//div[@class='nitrio-modal-body']//div[@class='nitrio-modal-margin'])[3]//div[contains(@class,'select-topic-autocomplete')]")).click();
	
	//save button
	driver.findElement(By.xpath("(//div[@class='nitrio-modal-body']//button[contains(text(),'Save')])[1]")).click();
	
	//check new recommendation created
	
	WebDriverWait w3 = new WebDriverWait(driver,30);
	w3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Recommendations')]")));
	
	//search for my recommendation
	driver.findElement(By.xpath("//div[@class='nitrio-searchbar-container']//input")).sendKeys("Srinivas");
	
	Thread.sleep(1000);
	
	name = driver.findElement(By.xpath("//div[@class='nitrio-recommendations']//table//tbody/tr/td[4]")).getText();
	
	if("Srinivas".equals(name))
	{
		System.out.println("The added recommendation is available in the recommendations list");
		
	}
	else
	{
		System.out.println("The added recommendation is NOT available in the recommendations list");
		
	}
	
	//delete recommendation
	
	driver.findElement(By.xpath("//div[@class='nitrio-recommendations']//table//tbody")).click();
	
	WebDriverWait w4 = new WebDriverWait(driver,30);
	w4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Delete')]")));
	
	driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
	
	a=driver.switchTo().alert();
	a.accept();
	
	}

}


