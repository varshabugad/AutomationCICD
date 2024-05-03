package Framework_Tutorial;
import java.awt.ItemSelectable;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Framework_Tutorial.pageObjects.landingPage;
public class Framework_TestCase {

	public static void main(String[] args) {
		String itemToFind= "ZARA COAT 3";
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		landingPage lp =new landingPage(driver);
	
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.findElement(By.id("userEmail")).sendKeys("vkharage@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Varsha#1");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> iteams=driver.findElements(By.cssSelector(".mb-3"));
				//driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		WebElement block= iteams.stream().filter(temp->temp.findElement(By.cssSelector("b")).getText().equals(itemToFind)).findFirst().orElse(null);
		System.out.println(block.getText());
		block.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		////ul[@class='cartWrap ng-star-inserted']
		
		
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//WebElement foundcartItem = cartItems.stream().filter(temp->temp.findElement(By.xpath("//div[@class='cartSection']/h3")).getText().equals(itemToFind)).findFirst().orElse(null);
		Boolean foundIteam=cartItems.stream().anyMatch(temp->temp.getText().equalsIgnoreCase(itemToFind));
		Assert.assertTrue(foundIteam);	
		driver.findElement(By.xpath("//ul/li/button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		List<WebElement> selectionList=driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		//Select dropdown=new Select(driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		//dropdown.selectByValue("India");
		WebElement selectedItem = selectionList.stream().filter(temp->temp.getText().equalsIgnoreCase("India")).findAny().orElse(null);
		selectedItem.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		String text =driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
