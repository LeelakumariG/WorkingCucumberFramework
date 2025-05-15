package com.org.main.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.org.main.TestComponents.BaseTest;
import com.org.main.pageobject.CartPage;
import com.org.main.pageobject.CheckoutPage;
import com.org.main.pageobject.OrderPage;
import com.org.main.pageobject.ProductCatalogue;
import com.org.main.pageobject.SuccessPage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import data.DataReader;

public class SubmitOrderTest extends BaseTest {

	//String productName = "ZARA COAT 3";
	String countryName = "India";	
	
	@Test(dataProvider="getData", groups = "purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = loginpage.loginCredential(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Assert.assertTrue(cartPage.Match(input.get("productName"))); 
		CheckoutPage chkpage = cartPage.Checkout();

		chkpage.SelectCountry(countryName);	
		SuccessPage success = chkpage.PlaceOrder();

		String message = success.CaptureMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Login details: "+ input.get("email") +"==== "+message+" ====product : "+input.get("productName"));
	}
	
	// To Verify ZARA COAT 3 is displaying in orders page  - this TEST CASE is DEPENDENT on PREVIOUS TEST	
	@Test (dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest () throws InterruptedException {
		
		String email = "latepractice@example.com";
		String pwd = "Practice@12345";
		String productName ="ZARA COAT 3";
		ProductCatalogue productCatalogue = loginpage.loginCredential(email, pwd);
		Thread.sleep(3000);
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Thread.sleep(3000);
		   Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));	
		System.out.println("Login details: "+ email +"=======product : "+productName);
	}
	
	
	
	@DataProvider	
	public Object[][] getData() throws IOException {
	    DataReader reader = new DataReader();
	    List<HashMap<String, String>> data = reader.getJSONDataToMap(
	        System.getProperty("user.dir") + "//src//test//java//data//purchaseOrder.json",
	        "UTF-8"
	    );

	    return new Object[][] {
	        { data.get(0) },
	        { data.get(1) }
	    };
	}
}



/*
 * HashMap <String, String> map1 = new HashMap<String, String>();
 * map1.put("email","latepractice@example.com");
 * map1.put("password","Practice@12345"); map1.put("productName","ZARA COAT 3");
 * 
 * HashMap <String, String> map2 = new HashMap<String, String>();
 * map2.put("email","testnew@example.com"); map2.put("password","Test@12345");
 * map2.put("productName","ADIDAS ORIGINAL");
 */

