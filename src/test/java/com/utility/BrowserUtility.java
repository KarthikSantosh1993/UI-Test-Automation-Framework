package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger log = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public static void unload() {
		driver.remove();
	}

	public BrowserUtility(String browserName) {
		log.info("Launching the browser using: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver.set(new SafariDriver());
		} else {
			log.error("Invalid browser name: " + browserName + "Please select chrome (or) firefox (or) safari only");
		}
	}

	public BrowserUtility(Browser browserName) {
		log.info("Launching the browser using: " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		} else if (browserName == Browser.SAFARI) {
			driver.set(new SafariDriver());
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
			}else {
				driver.set(new ChromeDriver());
			}
			

		} else if (browserName == Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options =new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new FirefoxDriver(options));
			}else {
				driver.set(new FirefoxDriver());
			}
			
		} else if (browserName == Browser.SAFARI) {
			driver.set(new SafariDriver());
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
		WebElement element = driver.get().findElement(locator);
		log.info("Element found. Performing Click operation");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		log.info("Finding Element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		log.info("Element found. Entering text as: " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		log.info("Finding Element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		log.info("Element found. Returning the value as: " + element.getText());
		return element.getText();
	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = dateFormat.format(date);

		String path = System.getProperty("user.dir") + "/screenshots/" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public void quitBrowser() {
		if (getDriver() != null) {
			getDriver().quit();
			unload(); // Critical step
		}
	}

}
