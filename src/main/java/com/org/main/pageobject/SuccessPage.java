package com.org.main.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.main.AbstractComponents.AbstractComponents;

public class SuccessPage extends AbstractComponents {

	WebDriver driver;

	public SuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	public String CaptureMessage() {
	String message = successMessage.getText();
	return message;
	}
		
	}

