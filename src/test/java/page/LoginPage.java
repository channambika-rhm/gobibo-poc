package page;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{

		@FindBy(css="span.logSprite.icClose")
		private WebElement closeBTN;
		
		@FindBy(xpath="//input[@class='loginCont__input']")
		private WebElement loginTB;
		
		@FindBy(xpath="//h3[text()='Login/Signup']")
		private WebElement loginTITLE;
		
		@FindBy(xpath="//div/label[text()='City']")
		private WebElement cityDD;
		
		@FindBy(xpath="//input[@placeholder='Enter City name']")
		private WebElement cityTextField;
		
		@FindBy(xpath="//div/li")
		private List<WebElement> cityLIST;
		
		@FindBy(xpath="//button[text()='Lets Go']")
		private WebElement goBTN;
		
		@FindBy(css="span.icSuccessSmall")
		private WebElement successMARK;
		
		public LoginPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}
		
		public void enterMobileNumber(String mn)
		{
			loginTB.sendKeys(mn);
		}
		
		public boolean isLoginPageDisplayed() {
			return loginTITLE.isDisplayed();
		}
		
		public void clickCloseButton() {
			closeBTN.click();
		}
		
		public void clickCityDropDown() {
			cityDD.click();
		}
		
		public void enterCityNmae(String city) {
			cityTextField.sendKeys(city);
		}
		
		public void selectCity(WebDriver driver,String city) {
			for(WebElement option: cityLIST) {
				String cityFromList = option.getText();
				cityFromList.toLowerCase();
				if(cityFromList.contains(city.toLowerCase())) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
				}
			}
		}
		
		public void clickLetsGoButton() {
			goBTN.click();
		}
}
