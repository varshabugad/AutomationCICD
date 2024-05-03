package Framework_Tutorial.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework_Tutorial.abstractComponenets.abstractComponent;

public class landingPage extends abstractComponent{
	public WebDriver driver;
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(xpath="//div[@class='toast-bottom-right toast-container']")
	 WebElement errormsg;
	public productCatelogue action(String Email,String Password )
	{
		userEmail.sendKeys(Email);
		userPassword.sendKeys(Password);
		login.click();
		System.out.println(errormsg.getText());
		return new productCatelogue(driver);
	}

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	public   String getErrorMessage()
	{
		waitForWebElemetsToAppear(errormsg);
			return errormsg.getText();
		
		}
	

}
