package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;

public class DummyTest1 extends BaseClass{
	
	@Test
	public void dummyTest() {

	    String title = driver.getTitle();

	    System.out.println("Page Title: " + title);

	    Assert.assertTrue(title.contains("OrangeHRM"));

	}


}
