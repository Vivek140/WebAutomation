package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseClass{
     private LoginPage loginPage;
     private HomePage homePage;
     
     
     @BeforeMethod
     public void setupPages() {
    	 loginPage= new LoginPage(getDriver());
    	 homePage= new HomePage(getDriver());
     }
     
     @Test
     public void verifyValidateLogin() {
    	 loginPage.login("Admin", "admin123");
    	
    	 Assert.assertTrue(homePage.isAdminVisible(),"admin verfied succes");
    	
    	 homePage.logout();
    	 staticwait(2);
     }
     
     @Test
     public void verifyInvalidTest() {
    	 loginPage.login("admin","admn123");
    	 String expectedError="Invalid credentials";
    	 Assert.assertTrue(loginPage.verifyErrorMessage(expectedError));
     }
     
}
