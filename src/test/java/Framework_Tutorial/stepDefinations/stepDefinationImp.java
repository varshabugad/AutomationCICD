package Framework_Tutorial.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework_Tutorial.pageObjects.landingPage;
import Framework_Tutorial.pageObjects.productCatelogue;
import Framework_Tutorial.testComponents.baseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImp extends baseTest{
	public landingPage lp;
	productCatelogue productCatelogue;
	@Given("I landed on ecommerce website")
	public void I_landed_on_ecommerce_website() throws IOException
	{
		lp=launchDriver();
		
	}

	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String pwd)
	{
		lp.goTo(); 
		productCatelogue=lp.action(username,pwd);

	}
	
	@When("^Add product (.+) to cart$")
	public void When_Add__product_to_cart(String productName)
	{
		productCatelogue.listingProducts();
		productCatelogue.getsingleProduct(productName);
	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		productCatelogue.addToCart(productName);
		//System.out.println(block.getText());
		productCatelogue.goToCart();
		productCatelogue.cartItems(productName);

	}
	
	@Then("{string}message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		System.out.println("Message from statement - "+string);
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertTrue(lp.getErrorMessage().equalsIgnoreCase(string));

	}
	
}
