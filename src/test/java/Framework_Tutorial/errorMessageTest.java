package Framework_Tutorial;
import java.awt.ItemSelectable;
import java.io.IOException;
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
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Framework_Tutorial.pageObjects.landingPage;
import Framework_Tutorial.pageObjects.productCatelogue;
import Framework_Tutorial.testComponents.baseTest;
public class errorMessageTest extends baseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer=Framework_Tutorial.testComponents.Retry.class)
	public void submitOrder() throws IOException {
		//String itemToFind= "ZARA COAT 3";
		lp.goTo();
		lp.action("vkharage@gmail.com","Valrsha#1");
		//landingPage lp=new landingPage(driver);
		System.out.println(lp.getErrorMessage());
		Assert.assertTrue(lp.getErrorMessage().equalsIgnoreCase("Incorrect email or password."));
	}

}
