package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.HotelsPage;
import page.LoginPage;

public class ChooseWhereToCity extends BaseTest {

	
	@Test(priority = 1,description="Verify that entering Delhi in the text box Where to under Book Hotels and Home Stays page working")
	public void test_TC_003_ChooseWhereToCity() {
//		1. Get Test Data 
		String city = Utility.getXLCellData(XL_PATH, "ChooseWhereToCity", 1, 1);
		
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
		
//		5. The user should be able to choose the city Delhi from the drop down box
		test.info("User successfully chose the city Delhi from the drop down box");
		boolean result = hotelsPage.verifyCityIsSelected(wait,city);
		Assert.assertTrue(result );
	}

}
