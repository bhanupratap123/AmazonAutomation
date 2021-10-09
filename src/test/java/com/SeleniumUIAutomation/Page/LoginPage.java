package com.SeleniumUIAutomation.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.seleniumUIAutommation.Helper.TestBaseHelper;

public class LoginPage extends TestBaseHelper {

	@FindBy(xpath = "//input[@name='username']")
	private WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement logInButton;

	@FindBy(xpath = "//p[text()='Experience the difference']")
	private WebElement logoLine;

	@FindBy(xpath = "img[@class='logo']")
	private WebElement image;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		//log.debug("Login method");
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		logInButton.click();

	}

	public void verifyLogo() {
		Assert.assertTrue(isDisplayed(image, true));
		Assert.assertTrue(isDisplayed(logoLine, true));
	}

	

}
