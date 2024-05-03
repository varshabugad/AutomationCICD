package Framework_Tutorial.abstractComponenets;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework_Tutorial.pageObjects.OrderPage;

public class abstractComponent {


	public WebDriver driver;
	public abstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

		}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartbutton;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement ordersbutton;
	
	public void waitForElemetsToAppear(By products)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(products));


	}
	
	public void waitForInvisibleElemetsToAppear(WebElement animating)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(animating));


	}
	public  void waitForWebElemetsToAppear(WebElement errormsg)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errormsg));
	}
	
	public void  goToCart()
	{
		cartbutton.click();
	}
	public OrderPage goToOrder()
	{
		ordersbutton.click();
		OrderPage orderpage = new OrderPage(driver); 
		return orderpage;
	}
}
