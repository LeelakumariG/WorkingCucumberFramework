package com.org.main.pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.main.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy (css =".ng-animating")
	WebElement animating;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.xpath("//div[@id='toast-container']");
	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement filterProduct(String productName) {		
		WebElement item = getProductList().stream()
				.filter(eproduct -> eproduct.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null); // Finding the desired product		
		return item;
	}
	
	public void addProductToCart(String productName) {
		 WebElement item = filterProduct(productName);		 
		item.findElement(addToCart).click(); // Adding to cart
		waitForElementToAppear(toastContainer);
		waitForElementToDisapper(animating, spinner);		
	}
	
}

