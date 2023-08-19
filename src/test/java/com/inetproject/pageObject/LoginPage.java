package com.inetproject.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "txtLoginname")
	WebElement txtusername;

	@FindBy(id = "txtPassword")
	WebElement userPasswaord;

	@FindBy(id = "btnSubmit")
	WebElement submitbtn;

	public void setUserName(String uname) {
		txtusername.sendKeys(uname);
	}

	public void setPassword(String Pcode) {
		userPasswaord.sendKeys(Pcode);
	}

	public void clickSubmit() {
		submitbtn.click();
	}
}
