package com.inetproject.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetproject.Utilities.ReadConfig;

public class BaseClass {

	ReadConfig rc = new ReadConfig();
	public String baseURL = rc.geturl();
	public String username = rc.getusername();
	public String password = rc.getpassword();
	public static WebDriver driver;
	public static Logger logger;

	public ExtentHtmlReporter htmlreport;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite
	public void ExtentReport() {

		htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") + "./Reports/extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);

		htmlreport.config().setDocumentTitle("THP Automation Report");
		htmlreport.config().setReportName("TeleHealth Automation Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getTestName());
		} else {
			test.log(Status.SKIP, result.getTestName());
		}
	}

	@AfterTest
	public void TearDown() {
		// to write or update test information to reporter
		extent.flush();
	}

	@Parameters("Browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger("InetProjectLog");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", rc.chromepath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", rc.chromepath());
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			System.setProperty("webdriver.chrome.driver", rc.chromepath());
			driver = new ChromeDriver();
		}
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	public void CaptureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot Taken");
	}

}
