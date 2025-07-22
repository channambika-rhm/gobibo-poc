package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.HotelsPage;
import page.LoginPage;

public class CreateNewAccount extends BaseTest
{
	@Test(priority = 1,description = "Verify that the user can successfully create an account with valid inputs in all fields.")
	public void test_TC_002_CreateNewAccount()
	{
//		Get Test Data 
		String mn=Utility.getXLCellData(XL_PATH, "CreateNewAccount",1, 0);
		String city = Utility.getXLCellData(XL_PATH, "CreateNewAccount", 1, 1);
		
//		1. enter valid mn
		test.info("Enter valid mobile number in required field"+mn);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterMobileNumber(mn);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}

		// Click on City drop down field
		test.info("Click on City drop down field");		
		loginPage.clickCityDropDown();
		
		// Enter City name
		test.info("Enter city name into the text field");
		loginPage.enterCityNmae(city);
		
		// Select City
		test.info("Select city name from the list");
		loginPage.selectCity(driver,city);
		
		// Click on Let's Go button
		test.info("Click on Let's Go button");
		loginPage.clickLetsGoButton();
		
		//	User should get a OTP and enter the OTP in the site for successful account creation
		test.info("User should get a OTP and enter the OTP in the site for successful account creation");
		HomePage homePage=new HomePage(driver);
		
		// home page should be displayed
		boolean result = homePage.verifyHomePageIsDisplayed(wait);
		Assert.assertTrue(result);
	}
}
