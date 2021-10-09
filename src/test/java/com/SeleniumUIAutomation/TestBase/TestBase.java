package com.SeleniumUIAutomation.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.SheetCollate;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.SeleniumUIAutomation.Page.HomePage;
import com.SeleniumUIAutomation.Page.LoginPage;
import com.seleniumUIAutommation.Helper.TestBaseHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	//Logger log = Logger.getLogger(TestBase.class);

	public static WebDriver driver;
	public static Properties prop;
	public static LoginPage loginPage;
	public static TestBaseHelper testBaseHelper;
	public static Workbook book;
	public static SheetCollate sheet;
	static String TEST_DATA_SHEET_PATH = "D:\\AmazonAutomated\\src\\main\\java\\com\\"
			+ "amazon\\automation\\TestData\\AmazonAutomationTestData.xlsx";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\bhanu\\SeleniumUIAutiomationDemo\\src\\"
					+ "test\\java\\com\\seleniumautomation\\testData\\application.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void intializeObject() {
		loginPage = new LoginPage(driver);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
	}

	@BeforeSuite
	public static void intialization() throws Throwable {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chrome = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver(chrome);

		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = prop.getProperty("url");
		driver.get(url);
		intializeObject();
	}

	public static void readTestDatafromExcel(String filename) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
	}
//
//		sheet = book.getSheet(filename)file;
//		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//			for (int j = 0; j <= sheet.getRow(0).getLastCellNum(); j++) {
//				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
//			}
//		}
		//return data;
	}

	public static HomePage login() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testBaseHelper = new TestBaseHelper(driver);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void failed() {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("D:/AmazonAutomated/screenshots/Failed.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void teardown() {
		System.gc();
		driver.quit();
	}

}
