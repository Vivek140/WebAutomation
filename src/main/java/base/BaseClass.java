package base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.io.*;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import utilities.LoggerManager;

public class BaseClass {
	
	protected static Properties prop;
	 protected  WebDriver driver;
	 public static final Logger logger =LoggerManager.getLogger();
	 
	 @BeforeSuite
	 public void loadconfig() throws IOException {
		 prop=new Properties();
		 FileInputStream fis= new FileInputStream("src/main/resources/config.properties");
		 prop.load(fis);
	 }
	 
	 @Parameters("browser")
	 @BeforeMethod
	 public void setup(String browser) throws IOException {

	     launchbrowser(browser);

	     configureBrowser();

	     staticwait(2);
	 }

	 private void launchbrowser(String browser) {

	     if(browser.equalsIgnoreCase("chrome")) {

	         driver = new ChromeDriver();
	     }

	     else if(browser.equalsIgnoreCase("edge")) {

	         driver = new EdgeDriver();
	     }

	     else {

	         throw new IllegalArgumentException("Browser not supported: " + browser);
	     }
	 }
	 
	 private void configureBrowser() {
		 int implicit= Integer.parseInt(prop.getProperty("implicitwait"));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
		 
		 driver.manage().window().maximize();
		 try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
		System.out.print("naviagtion faile"+e.getMessage());
		}
	 }
	 
	 public void staticwait(int seconds) {
		 LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	 }
	 
	 public WebDriver getDriver() {
		 return driver;
	 }
	 
	 public static Properties getprop() {
		 return prop;
	 }
	 
	 public void setDriver(WebDriver driver) {
		 this.driver=driver;
	 }

	 @AfterMethod
	 public void tearDown(ITestResult result) throws IOException {

	     // Check if test failed
	     if(result.getStatus() == ITestResult.FAILURE) {
	    	 File folder = new File("screenshots");

	    	 if(!folder.exists()) {
	    	     folder.mkdir();
	    	 }

	         // Take screenshot
	         File src = ((TakesScreenshot)driver)
	                 .getScreenshotAs(OutputType.FILE);

	         File dest = new File("screenshots/"
	                 + result.getName() + ".png");

	         FileHandler.copy(src, dest);

	         System.out.println("Screenshot captured");
	     }

	     driver.quit();
	 }
	 
//	 @AfterMethod
//	 public void teardown() {
//		 if(driver!=null) {
//			 try {
//				driver.quit();
//			} catch (Exception e) {
//				System.out.print("naviagtion faile"+e.getMessage());
//			}
//		 }
//	 }
	

}
