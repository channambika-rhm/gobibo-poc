package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.HotelsPage;
import page.LoginPage;

public class HotelPageNavigation extends BaseTest
{
	@Test(priority=1,description = "Verify that clicking the Hotels button navigates the user to the Hotels page")
	public void test_TC_001_HotelPageNavigationWithLogin()
	{
//		Get Test Data 
		String mn=Utility.getXLCellData(XL_PATH, "HotelPageNavigation",1, 0);
			
//		1. enter valid mn
		test.info("enter valid User Name:"+mn);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterMobileNumber(mn);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}

		//	4. home page should be displayed
		test.info("Home page should be displayed");
		HomePage homePage=new HomePage(driver);
		homePage.clickDoItLaterBTN();
		homePage.clickHotelLINK();
		
		//	5. hotels page should be displayed
		test.info("Hotels page should be displayed");
		HotelsPage hotelsPage = new HotelsPage(driver);
		boolean result = hotelsPage.verifyHotelsPageIsDisplayed(wait);
		Assert.assertTrue(result);
	}
	
	@Test(priority=1,description = "Verify that clicking the Hotels button navigates the user to the Hotels page")
	public void test_TC_001_1_HotelPageNavigationWithoutLogin()
	{
//		1. Go to login page
		test.info("Login page should be displayed");
		LoginPage loginPage=new LoginPage(driver);
		if(loginPage.isLoginPageDisplayed() == true) 
		{
//			2. CLick close button	
			test.info("Click close button on login page");
			loginPage.clickCloseButton();
			
//			3. home page should be displayed
			test.info("Open the Homepage");
			HomePage homePage=new HomePage(driver);
			
			if(homePage.isTitleDisplayed() == true) {
				test.info("Click the Hotel button");
				homePage.clickHotelLINK();
			}
						
//			4. hotels page should be displayed
			test.info("The user should be navigated to the Hotel page successfully");
			HotelsPage hotelsPage = new HotelsPage(driver);
			boolean result = hotelsPage.verifyHotelsPageIsDisplayed(wait);
			Assert.assertTrue(result);
		}		
	}
}
