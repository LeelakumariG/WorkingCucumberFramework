package com.org.main.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.main.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement>orderItem;


	public Boolean VerifyOrderDisplay (String productName) {
	Boolean match = orderItem.stream().anyMatch(ecart -> ecart.getText().equalsIgnoreCase(productName));
	return match;
	}

	}


