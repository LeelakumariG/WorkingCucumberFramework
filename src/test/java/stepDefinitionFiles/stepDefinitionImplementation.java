package stepDefinitionFiles;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.org.main.TestComponents.BaseTest;
import com.org.main.pageobject.CartPage;
import com.org.main.pageobject.CheckoutPage;
import com.org.main.pageobject.LoginPage;
import com.org.main.pageobject.ProductCatalogue;
import com.org.main.pageobject.SuccessPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImplementation extends BaseTest {
	
	public LoginPage loginpage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage chkpage;
	public SuccessPage success;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		loginpage = launchApplication();
		
	}

	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password (String username, String password) {
		productCatalogue= loginpage.loginCredential(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When ("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
        cartPage = productCatalogue.goToCartPage();
		
		Assert.assertTrue(cartPage.Match(productName)); 
		chkpage = cartPage.Checkout();	
		chkpage.SelectCountry("India");	
		success = chkpage.PlaceOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		String message = success.CaptureMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("{string} message is displayed")
	public void error_validation(String string) {
		Assert.assertEquals(string,loginpage.getErrorMessage());
		driver.close();
	}
	
}
