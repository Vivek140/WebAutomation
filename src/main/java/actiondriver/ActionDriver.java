package actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class ActionDriver {
   private WebDriver driver;
   private WebDriverWait wait;
   
   public ActionDriver(WebDriver driver) {
	   this.driver=driver;
	   int explicitwait =Integer.parseInt(BaseClass.getprop().getProperty("explicitwait"));
	   this.wait= new WebDriverWait(driver,Duration.ofSeconds(30));
   }
   
   private void waitForElementToBeClickable(By by) {
	   try {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	} catch (Exception e) {
		System.out.print("element is not clickable"+e.getMessage());
	}
   }
   
//   public void click(By by) {
//	   try {
//		   waitForElementToBeClickable(by);
//		driver.findElement(by);
//	} catch (Exception e) {
//		System.out.print("element is not clicked"+e.getMessage());
//	}
//   }
   public void click(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement element = wait.until(
	        ExpectedConditions.presenceOfElementLocated(locator)
	    );

	    // Scroll into view
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", element
	    );

	    // Wait clickable
	    wait.until(ExpectedConditions.elementToBeClickable(locator));

	    try {
	        element.click(); // normal click
	    } catch (Exception e) {
	        // fallback to JS click
	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].click();", element
	        );
	    }
	}
   
   public String getText(By by) {
	  try {
		  waitForElementToBeVisible(by);
		return driver.findElement(by).getText();
	} catch (Exception e) {
		System.out.print("text is not inputted"+e.getMessage());
		return "";

	}
   }
   
   public boolean compareText(By by,String expectedText) {
	   try {
		waitForElementToBeVisible(by);
		   String actualText= driver.findElement(by).getText();
		   System.out.print("Vivek"+ driver.findElement(by).getText());
		   if(actualText.equals(expectedText)) {
			   System.out.print("text is matching");
			   return true;
		   }
		   else {
			   System.out.print("text is not matching");
			   return false;
		   }
	} catch (Exception e) {
		System.out.print("text is not matching"+e.getMessage());
		return false;
	}
   }
   
   
   public boolean isDisplayed(By by) {
	   try {
		waitForElementToBeVisible(by);
		return driver.findElement(by).isDisplayed();
	} catch (Exception e) {
		System.out.print("element is not displayed"+e.getMessage());
		return false;
		
	}
   }
   
   public void waitForPageLoad(int timeOutSeconds) {
	   try {
		wait.withTimeout(Duration.ofSeconds(timeOutSeconds)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
				   .executeScript("return document.readyState").equals("complete"));
	} catch (Exception e) {
		System.out.print("page did not load"+e.getMessage());
	}
   }
   
   public void scrollToElement(By by) {
	   try {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		   WebElement element= driver.findElement(by);
		   js.executeScript("argument[0],scrollIntoView(true);", element);
	} catch (Exception e) {
		System.out.print("unable to locate element"+e.getMessage());
	}
   }
   
   public void enterText(By by,String value) {
	   try {
		   waitForElementToBeVisible(by);
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	} catch (Exception e) {
		System.out.print("text is not inputted"+e.getMessage());
	}
   }
   
   
   
   private void waitForElementToBeVisible(By by) {
	   try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	} catch (Exception e) {
		System.out.print("element is not visible"+e.getMessage());
	}
   }
}
