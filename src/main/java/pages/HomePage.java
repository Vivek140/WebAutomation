package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;

public class HomePage {
    
	private ActionDriver actionDriver;
	private By adminTab=By.xpath("//span[text()='Admin']");

	private By userIdButton= By.className("oxd-userdropdown-name");
	private By logOutButton = By.xpath("//a[text()='Logout']");
	private By OrangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");
	
	public HomePage(WebDriver driver) {
		 this.actionDriver= new ActionDriver(driver);
		 
	 }
	
	public boolean isAdminVisible() {
		return actionDriver.isDisplayed(adminTab);
		
	}
	
	public boolean verifyHRMLogo() {
		return actionDriver.isDisplayed(OrangeHRMLogo);
	}
	
	public void logout() {
		actionDriver.click(userIdButton);
		actionDriver.click(logOutButton);
	}
}
