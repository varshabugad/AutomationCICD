package Framework_Tutorial.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework_Tutorial.abstractComponenets.abstractComponent;

public class OrderPage extends abstractComponent {
	WebDriver driver;
	WebElement block;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

		
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productNamesInTable;
	
	
	public boolean VerifyOrderDisplay(String itemToFind)
	{
		productNamesInTable.forEach(temp->System.out.println("All list items : "+temp.getText()));
		System.out.println("item to find - "+itemToFind);
		Boolean foundIteam=productNamesInTable.stream().anyMatch(temp->temp.getText().equalsIgnoreCase(itemToFind));
		return foundIteam;

	}
	
}
