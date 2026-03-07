package com.ui.pages;

import static com.constants.Env.QA;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtility {
	Logger log = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

	public HomePage(Browser browser,  boolean isHeadless) {
		super(browser, isHeadless);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		maximizeWindow();
	}

	public HomePage(WebDriver lambdaDriver) {
		super(lambdaDriver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		maximizeWindow();
		// TODO Auto-generated constructor stub
	}

	public LoginPage goToLoginPage() {
		log.info("Trying to perform click to go to Login page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	
	public void closeBrowser() {
		quitBrowser();
	}
}
