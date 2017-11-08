package com.corehybrid;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericKeywords {
	
	public WebDriver driver;
	public Properties prop;
	public ExtentTest test;
	
	public GenericKeywords(ExtentTest test){
		this.test=test;
		FileInputStream fs;
		try {
			prop = new Properties();
			fs = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\config.properties");
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void openBrowser(String browserType){
		if(browserType.equals("Chrome")){
			test.log(LogStatus.INFO, " opening browser " +browserType );
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browserType.equals("Firefox")){
			test.log(LogStatus.INFO, " opening browser " +browserType );
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/geckodriver.exe"); 
			driver = new FirefoxDriver();
		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
	}
	
	
	public void navigateTo(String url){
		test.log(LogStatus.INFO, "navigating to url + " + prop.getProperty(url));
		System.out.println(prop.getProperty(url));
		driver.get(prop.getProperty(url));
		
	}
	
	public void inputText(String locator, String data){
		test.log(LogStatus.INFO, "input to text + " + prop.getProperty(locator)  + " - " + data );
		getElement(locator).sendKeys(data);
	}
	
	public void getText(String locator){
		test.log(LogStatus.INFO, "get text + " + prop.getProperty(locator));
		getElement(locator).getText();
	}

	public void click(String locator) throws Exception{
		test.log(LogStatus.INFO, "click " + prop.getProperty(locator));
		getElement(locator).click();
		
	}	
	
	public void jsExecutor(String locator){
		test.log(LogStatus.INFO, "click using javascript executor " + prop.getProperty(locator));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();",locator); 
		
	}
	public void waitAndClick(String locator){
		 Actions action = new Actions(driver);
	  WebElement ele =driver.findElement(By.cssSelector("#topNavSearchSubmit3"));
		 action.moveToElement(ele).click().build().perform();
			
		
	}
	
	public void verifyTitle(String data){
		test.log(LogStatus.INFO, "verifyTitle");
		 Assert.assertTrue(driver.getTitle().matches(data));
	}
	
	public void assertValue(String locator , String data){
		test.log(LogStatus.INFO, "Assert Data " + prop.getProperty(locator)  + " - " + data );
		Assert.assertEquals(getElement(locator).getText(), data);
	}
	
	public void closeBrowser(){
		test.log(LogStatus.INFO, "Close Browser");
		driver.quit();
	}
	
	
	/*************Utility Functions********************/
	
	public WebElement getElement(String locatorKey){
		WebElement e = null;
		WebDriverWait wait = new WebDriverWait(driver,100);
		try{
		if(locatorKey.endsWith("_id")){
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(prop.getProperty(locatorKey)))));
			e= driver.findElement(By.id(prop.getProperty(locatorKey)));
		} else if(locatorKey.endsWith("_xpath")){
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(prop.getProperty(locatorKey)))));
			e= driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_css")){
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty(locatorKey)))));
			e= driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_link")){
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(prop.getProperty(locatorKey)))));
			e= driver.findElement(By.linkText(prop.getProperty(locatorKey)));
		}
		}catch(Exception ex){
			test.log(LogStatus.FAIL, "Failure in element extraction : " + locatorKey);
			takescreenshot();
			ex.printStackTrace();
			Assert.fail("Failure in element extraction : " + locatorKey);
		}
		
		return e;
		
	}
	
	
	
	/****************************/
	
	public void reportFailure(String failureMessage){
		takescreenshot();
		test.log(LogStatus.FAIL, failureMessage);
	}
	
	public void takescreenshot(){
		
	}
	
}
