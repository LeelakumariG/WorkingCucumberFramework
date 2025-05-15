package com.org.main.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.org.main.pageobject.CartPage;
import com.org.main.pageobject.OrderPage;

public class AbstractComponents {
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath ="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
		
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); // for synchronization
																					// issue- wait until all																					// product loads on page
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy)); // for synchronization
																					// issue- wait until all																					// product loads on page
	}
	
	public void waitForElementToDisapper(WebElement ele, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
			
	}
	
	public void waitForElementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
			
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();	
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();	
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}

