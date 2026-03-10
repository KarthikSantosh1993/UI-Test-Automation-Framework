package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class PaymentPage extends BrowserUtility {
	private static final By WARNING_ALERT_FOR_NO_PAYMENT_LOCATOR = By
			.xpath("//div[@id=\"HOOK_TOP_PAYMENT\"]/following::p");

	public PaymentPage(WebDriver driver) {
		super(driver);
	}

	public String verifyNoPaymentmethodsAlert() {
		return getVisibleText(WARNING_ALERT_FOR_NO_PAYMENT_LOCATOR);
	}
}
