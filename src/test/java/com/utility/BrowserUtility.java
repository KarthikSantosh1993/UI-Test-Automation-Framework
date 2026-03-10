package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private Logger log = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver.get();
	}

	@SuppressWarnings("static-access")
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	public static void unload() {
		driver.remove();
	}

	public BrowserUtility(String browserName) {
		log.info("Launching the browser using: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver.set(new SafariDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			log.error("Invalid browser name: " + browserName + "Please select chrome (or) firefox (or) safari only");
		}
	}

	public BrowserUtility(Browser browserName) {
		log.info("Launching the browser using: " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.SAFARI) {
			driver.set(new SafariDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		log.info("Launching the browser using: " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				// Disable leak detection and password manager
				prefs.put("profile.password_manager_leak_detection", false);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);

				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		} else if (browserName == Browser.SAFARI) {
			driver.set(new SafariDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}
	}

	public void goToWebsite(String url) {
		log.info("Navigating to the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		log.info("Maximizing the window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		log.info("Finding Element with locator " + locator);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		log.info("Element found. Performing Click operation");
		element.click();
	}

	public void clickOnCheckbox(By locator) {
		log.info("Finding Element with locator " + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		log.info("Element found. Performing Click operation on checkbox");
		element.click();
	}

	public void clickOn(WebElement element) {
		log.info("Element found. Performing Click operation");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		log.info("Finding Element with locator " + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		log.info("Element found. Entering text as: " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public void clearText(By locator) {
		log.info("Finding Element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		log.info("Element found. Clearing the textbox field");
		element.clear();
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		log.info("Finding Element with locator " + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		log.info("Element found. pressing key: " + keyToEnter);
		element.sendKeys(keyToEnter);
	}

	public void selectFromDropdownByText(By dropdownlocator, String textToSelect) {
		log.info("Finding Element with locator " + dropdownlocator);
		WebElement element = driver.get().findElement(dropdownlocator);
		Select select = new Select(element);
		log.info("Selecting the option " + textToSelect);
		select.selectByVisibleText(textToSelect);
	}

	public void selectFromDropdownByValue(By dropdownlocator, String valueToSelect) {
		log.info("Finding Element with locator " + dropdownlocator);
		WebElement element = driver.get().findElement(dropdownlocator);
		Select select = new Select(element);
		log.info("Selecting the option " + valueToSelect);
		select.selectByValue(valueToSelect);
	}

	public String getVisibleText(By locator) {
		log.info("Finding Element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		log.info("Element found. Returning the value as: " + element.getText());
		return element.getText();
	}

	// this method is used in getAllVisibleText(locator) method
	public String getVisibleText(WebElement element) {
		log.info("Returning the value as: " + element.getText());
		return element.getText();
	}

	public List<String> getAllVisibleText(By locator){
		log.info("Finding All Element with locator " + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		log.info("Elements found. Printing the list of web elements");
		List<String> visibleTextList = new ArrayList<String>();

		for (WebElement element : elementList) {
			System.out.println("product name: " + getVisibleText(element));
			visibleTextList.add(getVisibleText(element));

		}
		return visibleTextList;
	}

	public List<WebElement> getAllElements(By locator) {
		log.info("Finding All Element with locator " + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		return elementList;
	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = dateFormat.format(date);
		String path = "./screenshots/" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public void acceptAlertPopup() {
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = driver.get().switchTo().alert().getText();
		System.out.println("Alert says: " + alertText);
		driver.get().switchTo().alert().accept();
	}

	public void quitBrowser() {
		if (getDriver() != null) {
			getDriver().quit();
			unload(); // Critical step
		}
	}

}
