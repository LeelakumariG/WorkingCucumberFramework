package com.org.main.resources;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	@BeforeTest
	public static ExtentReports getReportObject() {

		// ExtentReports ; ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results-Leela"); // you can provide customized name
		reporter.config().setDocumentTitle("Test Results by Leela"); // you can provide customized name


		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Leela");
		return extent;
	}

}
