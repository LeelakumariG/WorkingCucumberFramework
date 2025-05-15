package com.org.main.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.org.main.pageobject.LoginPage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
// demo update
		WebDriver driver = new ChromeDriver();

		String productName = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
	// 1. Login Scenario
		driver.findElement(By.id("userEmail")).sendKeys("latepractice@example.com");
		driver.findElement(By.id("userPassword")).sendKeys("Practice@12345");
		driver.findElement(By.id("login")).click();

		// 2. Add To Cart Scenario
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); // for synchronization
																							// issue- wait until all
																							// product loads on page

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement item = products.stream()
				.filter(eproduct -> eproduct.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null); // Finding the desired product

		item.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // Adding to cart

		// 3. Navigating to Cart page
		/*
		 * synchronization- 1) a message appears for a sec synchronization -2) a
		 * progressing circular icon runs on page for few secs synchronization -3) even
		 * if progressing circular icon disappears, an invisible spinner keeps running
		 * Wait until all 1) 2) 3) completely disappears on page, so next webelement can
		 * be clicked without interception
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

		WebElement cartButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
		cartButton.click();

		// 4. Validate if the product added is matching to the expected product
		List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItem.stream().anyMatch(ecart -> ecart.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		// 5. Navigate to Checkout page and Place Order
		driver.findElement(By.cssSelector(".totalRow button")).click(); // checkout clicked

		// Handling autosuggest dropdown through Action Class
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();        // Order placed
		// By.xpath("(//button[contains(@class,'ta-item')])[2]")
		// By.cssSelector(".ta-item:nth-of-type(2)"

		// 6. Capturing Success Message and comparing with actual and expected 
		driver.findElement(By.cssSelector(".action__submit")).click();
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}
}
