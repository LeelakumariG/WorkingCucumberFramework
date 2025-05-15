package com.org.main.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.main.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement desiredCountrty;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By taresult = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {
	Actions a = new Actions(driver);	
	a.sendKeys(country, countryName).build().perform();
	waitForElementToAppear(taresult);
	desiredCountrty.click();
	}
	
	public SuccessPage PlaceOrder() {
		submit.click();
		SuccessPage success = new SuccessPage(driver);
		return success;
		}
		
	}

