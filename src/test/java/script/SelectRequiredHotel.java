package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HotelsPage;

public class SelectRequiredHotel extends BaseTest{

	@Test(priority=1,description="Verify that the user can select Pride Plaza Hotel Aerocity New Delhi")
	public void test_TC_006_SelectRequiredHotel()
	{
//		1. Get Test Data 
		String city = Utility.getXLCellData(XL_PATH, "SelectRequiredHotel", 1, 0);
		String checkInMonth = Utility.getXLCellData(XL_PATH, "SelectRequiredHotel", 1, 1);
		String checkInDateStr = Utility.getXLCellData(XL_PATH, "SelectRequiredHotel", 1, 2);
		String checkOutDateStr = Utility.getXLCellData(XL_PATH, "SelectRequiredHotel", 1, 3);
		String hotelName = Utility.getXLCellData(XL_PATH, "SelectRequiredHotel", 1, 4);
		
		int checkInDate = Integer.parseInt(checkInDateStr);
		int checkOutDate = Integer.parseInt(checkOutDateStr);
		
//		2. Go to Hotels page
		test.info("Hotels page should be displayed");	
		driver.navigate().to("https://www.goibibo.com/hotels/");

		HotelsPage hotelsPage = new HotelsPage(driver);
		
//		3. Click on Where to input field
		test.info("Click on Where to input field");
		hotelsPage.clickWhereToDropDown();
		try {
			hotelsPage.typeString(robot, city,wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		4. Select required city from Where to list
		test.info("Select required city from Where to list");
		hotelsPage.selectWhereTo(driver,city,wait);
		
//      5. Locate the check in and check out drop down boxes
		test.info("Locate the check in and check out drop down boxes");
		hotelsPage.clickCheckInDropDown(driver, wait);		
	
//		6. Choose the selected dates
		test.info("Choose the selected dates");
		hotelsPage.selectCheckInAndOutDates(driver, wait, checkInMonth, checkInDate, checkOutDate);
		
//		7. Click on Search button
		test.info("Click on Search button");
		hotelsPage.clickSearchButton(wait);
		
//		8.User should be navigated to the hotel room details page displaying availability and booking options
		test.info("User successfully navigated to the hotel room details page displaying availability and booking options");
		hotelsPage.selectHotel(driver, wait, hotelName);
		boolean result = hotelsPage.verifyRoomDetailsDisplayed(wait);
		Assert.assertTrue(result);
	}
}
