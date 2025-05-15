package com.org.main.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.main.TestComponents.BaseTest;
import com.org.main.TestComponents.Retry;
import com.org.main.pageobject.CartPage;
import com.org.main.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
//
	@Test (groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String email = "xxlatepractice@example.com";
		String pwd = "Practice@12345";
		String productName ="ZARA COAT 3";
		loginpage.loginCredential(email, productName);
		Assert.assertEquals("Incorrect email or password.",loginpage.getErrorMessage());	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String email = "latepractice@example.com";
		String pwd = "Practice@12345";
		String productName ="ZARA COAT 3";
		ProductCatalogue productCatalogue = loginpage.loginCredential(email, pwd);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
		
		Assert.assertFalse(cartPage.Match(productName)); 	
	}
		
}


