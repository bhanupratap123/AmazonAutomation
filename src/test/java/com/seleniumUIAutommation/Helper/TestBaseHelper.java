package com.seleniumUIAutommation.Helper;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBaseHelper {
	//Logger log = Logger.getLogger(TestBaseHelper.class);

	WebDriverWait wait;
	public WebDriver driver;
	Actions action;
	Robot robot;
	public final int Implicitly_Wait = 10;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// Implicit Wait
	public boolean isDisplayed(WebElement element, Boolean wait) {
		Boolean flag = false;
		if (wait) {
			element = this.wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			this.isDisplayed(element, true);
			flag = true;
		}
		return flag;
	}
	
	public TestBaseHelper(WebDriver driver) {
		this.driver=driver;
	}


	public void implicityWaitUntil(int timeUnitInSecond) {
		driver.manage().timeouts().implicitlyWait(timeUnitInSecond,TimeUnit.SECONDS);
	}
	

	// Handle locator type
	public static By byLocator(final String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
		} else if (locator.startsWith("Link=")) {
			result = By.linkText(locator.replace("Link=", ""));
		} else if (locator.startsWith("xpath=")) {
			result = By.xpath(locator.replace("xpath=", ""));
		} else if (locator.startsWith("(//")) {
			result = By.xpath(locator);
		} else {
			result = By.id(locator);
		}
		return result;
	}

	public void mouseClickOnElement(final String element) {

	}

	/*
	 * Desc : This method Explicitly wait for element for 10 Seconds;
	 * 
	 */
	public void elementTobeClickable(WebElement element) {
		wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * Desc: This method wait Until Element Appears
	 * 
	 */

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Implicitly_Wait, TimeUnit.SECONDS);
	}

	// this method will set frame to top
	public void switchToTop() {
		driver.switchTo().defaultContent();
	}

	public void mouseMovement(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		;
		action.click(element);
	}

	public void clickthroughJSexecutor(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void checkWhetherElementTakingDesiredTimeOrNot(WebElement element, int time) {
		driver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
		long startTimeOut = System.currentTimeMillis();
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], time);");
		if (startTimeOut - System.currentTimeMillis() >= time) {
		//	log.info(element + " Element is more time as desired time " + time);
		}

	}
	public void click(WebElement element) {
		element.click();
	}
	

}