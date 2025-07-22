package script;

import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Utility;
import page.HotelsPage;

public class SelectCheckInCheckOut extends BaseTest{
	
	@Test(priority=1,description="Verify that clicking check in and check out dates are selected for 25Jul25 to 27Jul25")
	public void test_TC_004_SelectCheckInCheckOut()
	{
//		1. Get Test Data 
		String city = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 0);
		String checkInMonth = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 1);
		String checkInDateStr = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 2);
		String checkOutDateStr = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 3);
		String checkInFullDate = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 4);
		String checkOutFullDate = Utility.getXLCellData(XL_PATH, "SelectCheckInCheckOut", 1, 5);
		
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
		
//      Locate the check in and check out drop down boxes
		test.info("Locate the check in and check out drop down boxes");
		hotelsPage.clickCheckInDropDown(driver, wait);		
	
//		Choose the selected dates
		test.info("Choose the selected dates");
		hotelsPage.selectCheckInAndOutDates(driver, wait, checkInMonth, checkInDate, checkOutDate);
		
//		The user should be able to select check in and check out dates for 25Jul25 to 
		test.info("The user is successfully selected check in and check out dates for 25Jul25 to 27Jul25");
		boolean result1 = hotelsPage.verifyCheckInDateIsSelected(checkInFullDate, wait);
		Assert.assertEquals(result1, true);
		
		boolean result2 = hotelsPage.verifyCheckOutDateIsSelected(checkOutFullDate, wait);
		Assert.assertEquals(result2, true);
	}
}
