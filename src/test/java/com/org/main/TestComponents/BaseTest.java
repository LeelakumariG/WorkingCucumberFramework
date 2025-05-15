package com.org.main.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.main.pageobject.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//org//main//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName =  System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();			
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			
			driver= new ChromeDriver(options);	
			driver.manage().window().setSize(new Dimension(1440,900)); // helps to run full screen
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
  System.setProperty("webdriver.gecko.driver", "C://Users//Leela//Documents//Eclipse//Downloads//Drivers//geckodriver-v0.36.0-win64//geckodriver.exe");
		    driver = new FirefoxDriver();
		}


		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun= true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();
		loginpage = new LoginPage(driver);
		loginpage.goToURL();
		return loginpage;
	}
	
	@AfterMethod (alwaysRun= true)
	public void browserClose() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJSONDataToMap(String filePath) throws IOException {

		// Read JSON file to a string
		String jsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");

		// Convert JSON string to List<HashMap<String, String>> 
		//using Jackson Databind method 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		 File src = ts.getScreenshotAs(OutputType.FILE);
		    File dest = new File(System.getProperty("user.dir") + "//reports//"+testCaseName+".png");
		    FileUtils.copyFile(src, dest);	    
			return System.getProperty("user.dir") + "//reports//"+testCaseName+".png";
	}

}


