package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;

public class LoginPage {
     
	private ActionDriver actionDriver;
	 private By usernameField = By.name("username");
	 private By passwordField = By.cssSelector("input[type='password']");
	 private By loginbutton = By.xpath("//button[text()=' Login ']");
	 private By errormessage = By.xpath("//p[text()='Invalid credentials']");	
	 
	 public LoginPage(WebDriver driver) {
		 this.actionDriver= new ActionDriver(driver);
		 
	 }
	 
	 public void login(String username, String password) {
		 
		 actionDriver.enterText(usernameField, username);
		 actionDriver.enterText(passwordField, password);
		 actionDriver.click(loginbutton);
	 }
	 
	 public boolean isErrorMessageDisplayed() {
		 return actionDriver.isDisplayed(errormessage);
	 }
	 
	 public String getErrorText() {
		 return actionDriver.getText(errormessage);
	 }
	 
	 public boolean verifyErrorMessage(String expectedError) {
		 return actionDriver.compareText(errormessage, expectedError);
	 }
}
