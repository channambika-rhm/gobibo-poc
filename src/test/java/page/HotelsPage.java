package page;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HotelsPage {
	
	@FindBy(xpath="//h2[contains(text(),'Book Hotels')]")
	private WebElement hotelPageTITLE;
	
	@FindBy(xpath="//span[text()='Where to']")
	private WebElement whereToDD;
	
	@FindBy(xpath="//ul[@role='listbox']/li")
	private List<WebElement> cityOptionsLIST;
	
	@FindBy(xpath="//span[text()='Where to']/following-sibling::p")
	private WebElement citySelected;
	
	@FindBy(xpath="//span[text()='Check-in']/..")
	private WebElement checkInDD;
	
	@FindBy(xpath="//span[text()='Check-out']/..")
	private WebElement checkOutDD;
	
	@FindBy(xpath="//div[contains(@class,'FswPickerContBody')]")
	private WebElement currentMonthBody;
	
	@FindBy(xpath="//div[contains(@class,'DayAndDateRightWrapper')]")
	private WebElement nextMonthBody;
	
	@FindBy(xpath="//span[contains(@data-testid,'currentCalendarMonthName')]")
	private WebElement currentMonth;
	
	@FindBy(xpath="//div[contains(@class,'DayAndDateLeftWrapper')]/ul[contains(@class,'styles__DateWrapDiv')]/li")
	private List<WebElement> currentMonthDates;
	
	@FindBy(xpath="//span[contains(@class,'FswFldHeading') and contains(text(),'Check-in')]/following-sibling::p")
	private WebElement selectedCheckInDate;
	
	@FindBy(xpath="//span[contains(@class,'FswFldHeading') and contains(text(),'Check-out')]/following-sibling::p")
	private WebElement selectedCheckOutDate;
	
	@FindBy(xpath="//a[contains(@class,'SearchBlock')]")
	private WebElement searchBTN;
	
	@FindBy(xpath="//h3[contains(@class,'HotelList') and contains(text(),'Showing Properties')]")
	private WebElement hotelListSectionHDR;
	
	@FindBy(xpath="//a[contains(@class,'PropertyName')]")
	private List<WebElement> hotelsLIST;
	
	public HotelsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public boolean verifyHotelsPageIsDisplayed(WebDriverWait wait)
	{
		try 
		{
				wait.until(ExpectedConditions.visibilityOf(hotelPageTITLE));
				Reporter.log("Hotels page is displayed",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log("Hotels page is not displayed",true);
				return false;
		}		
	} 
	public boolean verifyCityIsSelected(WebDriverWait wait,String city)
	{
		try 
		{
				wait.until(ExpectedConditions.visibilityOf(citySelected));
				Reporter.log(city + " is Selected",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log(city + " is not Selected",true);
				return false;
		}		
	}
	
	public void clickWhereToDropDown() {
		whereToDD.click();
	}
	
    // Helper method to type characters using Robot
    public void typeString(Robot robot, String text, WebDriverWait wait) throws InterruptedException {
    	
        for (char c : text.toCharArray()) {
            boolean upperCase = Character.isUpperCase(c);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (upperCase) robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (upperCase) robot.keyRelease(KeyEvent.VK_ENTER);
            wait.until(ExpectedConditions.visibilityOfAllElements(cityOptionsLIST));
        }
    }

	public void selectWhereTo(WebDriver driver, String city, WebDriverWait wait) {
	    // Wait for the options to be present
		wait.until(ExpectedConditions.visibilityOfAllElements(cityOptionsLIST));
				
	    for (WebElement option : cityOptionsLIST) {
	        String text = option.getText();
	        if (text != null && text.contains(city)) {
	            // click using JavaScript
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	            break;
	        }
	    }
//	    wait.until(ExpectedConditions.visibilityOf(checkInDD));
//	    wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Check-in']/.."))));
//		wait.until(ExpectedConditions.elementToBeClickable(checkInDD));
	}
	
	public void selectCheckInAndOutDates(WebDriver driver, WebDriverWait wait,String checkInMonth,int checkInDate,int checkOutDate) {
		wait.until(ExpectedConditions.visibilityOf(currentMonthBody));
		if(currentMonthBody.isDisplayed() == true) {
			wait.until(ExpectedConditions.visibilityOf(currentMonth));
			if(currentMonth.getText().contains(checkInMonth)) {
				wait.until(ExpectedConditions.visibilityOfAllElements(currentMonthDates));
				boolean checkInClicked = false;

				for (WebElement date : currentMonthDates) {
				    String dateStr = date.getText().trim();

				    if (dateStr.isEmpty()) continue;

				    try {
				        int dateToCheck = Integer.parseInt(dateStr);

				        if (!checkInClicked && dateToCheck == checkInDate) {
				            date.click();
				            checkInClicked = true;
				            continue;
				        }

				        if (checkInClicked && dateToCheck == checkOutDate) {
				            date.click();
				            break;
				        }

				    } catch (NumberFormatException e) {
				        System.out.println("Invalid date text: " + dateStr);
				    }
				}
				
			}
		}
	}
	
	public void clickCheckInDropDown(WebDriver driver, WebDriverWait wait) {
		driver.navigate().refresh();
//		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Check-in']/.."))));			    
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkInDD);
		wait.until(ExpectedConditions.visibilityOf(currentMonthBody));
	}
	
	public boolean verifyCheckInDateIsSelected(String checkInFullDate, WebDriverWait wait) {
		try 
		{
				wait.until(ExpectedConditions.visibilityOf(selectedCheckInDate));
				Reporter.log(checkInFullDate + " is Selected",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log(checkInFullDate + " is not Selected",true);
				return false;
		}
	}
	
	public boolean verifyCheckOutDateIsSelected(String checkOutFullDate, WebDriverWait wait) {
		try 
		{
				wait.until(ExpectedConditions.visibilityOf(selectedCheckOutDate));
				Reporter.log(checkOutFullDate + " is Selected",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log(checkOutFullDate + " is not Selected",true);
				return false;
		}
	}
	
	public void clickSearchButton(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(selectedCheckInDate));
		wait.until(ExpectedConditions.visibilityOf(selectedCheckOutDate));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(searchBTN)));
		searchBTN.click();
	}

	public boolean verifyRoomDetailsDisplayed(WebDriverWait wait) {
		try 
		{
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[contains(@class,'HotelList') and contains(text(),'Showing Properties')]"))));
				Reporter.log(hotelListSectionHDR + " is displayed",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log(hotelListSectionHDR + " is not displayed",true);
				return false;
		}
		
	}
	
	public void selectHotel(WebDriver driver, WebDriverWait wait, String hotelName) {
		wait.until(ExpectedConditions.visibilityOfAllElements(hotelsLIST));
	    for (WebElement option : hotelsLIST) {
	        String text = option.getText();
	        if (text != null && text.contains(hotelName)) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	            break;
	        }
	    }
	}
}
