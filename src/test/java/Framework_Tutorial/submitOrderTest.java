package Framework_Tutorial;
import java.awt.ItemSelectable;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework_Tutorial.pageObjects.OrderPage;
import Framework_Tutorial.pageObjects.landingPage;
import Framework_Tutorial.pageObjects.productCatelogue;
import Framework_Tutorial.testComponents.baseTest;
public class submitOrderTest extends baseTest {
	

	@Test(dataProvider="getData",groups= {"purchase"},retryAnalyzer=Framework_Tutorial.testComponents.Retry.class)
	public void submitOrder(String email,String password,String product ) throws IOException {
		System.out.println("emaol - "+email+"pwd : "+password);

		lp.goTo();
		productCatelogue productCatelogue=lp.action(email,password);
		List<WebElement> iteams=productCatelogue.listingProducts();
		WebElement block=productCatelogue.getsingleProduct(product);	
		productCatelogue.addToCart(product);
		//System.out.println(block.getText());
		productCatelogue.goToCart();
		productCatelogue.cartItems(product);

	
	}

	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{		lp.goTo();
	String itemToFind= "ZARA COAT 3";
		productCatelogue productCatelogue=lp.action("vkharage@gmail.com","Varsha#1");
		OrderPage orderpage = productCatelogue.goToOrder();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(itemToFind));
	}



@DataProvider()
public Object[][] getData() throws IOException
{
	List<HashMap<String,String>> data = getJsonToHashMap(System.getProperty("user.dir")+"//src//test//java//Framework_Tutorial//Data//PurchaseOrder.json");
System.out.println(data.get(0)+" and "+data.get(1));
return new Object[][] {{data.get(0)},{data.get(1)}};
}
}