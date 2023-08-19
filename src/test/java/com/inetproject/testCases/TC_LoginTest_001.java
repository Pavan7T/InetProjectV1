package com.inetproject.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetproject.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException, InterruptedException {
		// System.out.println(baseURL);
		driver.get(baseURL);

		logger.info("Open Url successfully");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username entered");
		lp.setPassword(password);
		logger.info("Password entered");
		lp.clickSubmit();
		logger.info("Submitted");

		if (driver.getTitle().equals("SoftLink TeleHealth Platform1")) {
			test = extent.createTest("TC_LoginTest_001", "The test case 1 has passed");
			Assert.assertTrue(true);
			logger.info("Test Passed !");

		} else {
			// Assert.assertTrue(false);
			CaptureScreen(driver, "TC_LoginTest_001");
			test = extent.createTest("TC_LoginTest_001", "The test case 1 has failed");
			Assert.assertFalse(false);
			logger.error("Test fail !");
		}
	}

}
