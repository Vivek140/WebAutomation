package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseClass {
    
	private LoginPage loginPage;
    private HomePage homePage;
    
    
    @BeforeMethod
    public void setupPages() {
   	 loginPage= new LoginPage(getDriver());
   	 homePage= new HomePage(getDriver());
    }
    
    @Test
    public void verfiyOrangeHRMLogo() {
    	loginPage.login("Admin","admin123");
        Assert.assertTrue(homePage.verifyHRMLogo());
    	
    }
}
