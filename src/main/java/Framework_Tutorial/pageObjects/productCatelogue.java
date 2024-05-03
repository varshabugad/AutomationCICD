package Framework_Tutorial.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework_Tutorial.abstractComponenets.abstractComponent;

public class productCatelogue extends abstractComponent {
	WebDriver driver;
	WebElement block;
	public productCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

		
	@FindBy(css=".mb-3")
	List<WebElement> iteams;
	
	@FindBy(css=".ng-animating")
	WebElement animating;
	//		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

	
	//		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	//driver.findElement(By.xpath("//ul/li/button[@class='btn btn-primary']")).click();
	@FindBy(xpath="//ul/li/button[@class='btn btn-primary']")
	WebElement checkout;
	//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement search;
	
	//		List<WebElement> selectionList=driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));

	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> selectionList;
	//		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submitButton;
	//		String text =driver.findElement(By.className("hero-primary")).getText();

	@FindBy(className="hero-primary")
	WebElement text;
	
	By products=By.cssSelector(".mb-3");
	By productname= By.xpath("//button[@class='btn w-10 rounded']");
	By toast=By.cssSelector("#toast-container");
	By selectBox = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	By submit = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");
	By ByText= By.className("hero-primary");
	public List<WebElement> listingProducts()
	{
		waitForElemetsToAppear(products);
		return iteams;
	}
	public WebElement getsingleProduct(String itemToFind)
	{
		 block= listingProducts().stream().filter(temp->temp.findElement(By.cssSelector("b")).getText().equals(itemToFind)).findFirst().orElse(null);
			System.out.println("Block is : -"+block.getText());
		 return block;

	}
	//block.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

	public void addToCart(String product)
	{
		WebElement singleElement = getsingleProduct(product);
		
		System.out.println("In addToCart method :"+ block.getText()+"And single element is : "+singleElement.getText());
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(productname));
		
		singleElement.findElement(productname).click();
		waitForElemetsToAppear(toast);

		waitForInvisibleElemetsToAppear(animating);


	}
	public void cartItems(String itemToFind )
	{
		System.out.println("itemToFind : "+itemToFind);
		cartItems.forEach(temp->System.out.println("found iteam :"+temp.getText()));
		Boolean foundIteam=cartItems.stream().anyMatch(temp->temp.getText().equalsIgnoreCase(itemToFind));
		System.out.println("Iteam matched -"+foundIteam);
		checkout.click();
		search.sendKeys("ind");
		waitForElemetsToAppear(selectBox);
		WebElement selectedItem = selectionList.stream().filter(temp->temp.getText().equalsIgnoreCase("India")).findAny().orElse(null);
		selectedItem.click();
		waitForElemetsToAppear(submit);
		submitButton.click();
		waitForElemetsToAppear(ByText);
		String txt= text.getText();
		System.out.println("Text is -"+txt);
		System.out.println(txt.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		
		}
	
}
