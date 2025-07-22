package page;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class HomePage 
{
	@FindBy(xpath="//title[contains(text(),'Goibibo - Best Travel Website')]")
	private WebElement titleTXT;
	
	@FindBy(xpath="//h3[@class='successMsg__title']")
	private WebElement successMSG;
	
	@FindBy(xpath="//button[contains(text(),'later')]")
	private WebElement doItLaterLINK;
	
	@FindBy(xpath="//span[text()='Hotels']")
	private WebElement hotelsLINK;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public boolean isTitleDisplayed() {
		return titleTXT.isDisplayed();
	}
	
	public boolean verifyHomePageIsDisplayed(WebDriverWait wait)
	{
		try 
		{
				wait.until(ExpectedConditions.visibilityOf(successMSG));
				Reporter.log("Home Page is Displayed",true);
				return true;
		}
		catch (Exception e) 
		{
				Reporter.log("Home Page is NOT Displayed",true);
				return false;
		}
		
	}
	
	public void clickDoItLaterBTN() {
		doItLaterLINK.click();
	}
	
	public void clickHotelLINK() {
		hotelsLINK.click();
	}
	
}
