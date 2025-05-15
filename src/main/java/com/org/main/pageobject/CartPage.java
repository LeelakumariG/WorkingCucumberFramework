package com.org.main.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.main.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement>cartItem;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;

	public Boolean Match (String productName) {
	Boolean match = cartItem.stream().anyMatch(ecart -> ecart.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckoutPage Checkout() { 
		checkoutBtn.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}
	
}

