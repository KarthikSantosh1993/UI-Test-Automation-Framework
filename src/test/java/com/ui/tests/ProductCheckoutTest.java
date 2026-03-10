package com.ui.tests;

import static com.constants.Size.L;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.SearchResultPage;

public class ProductCheckoutTest extends TestBase {
	private static final String SEARCH_TERM = "Printed Summer Dress";
	private static final String EXPECTED_PAYMENT_WARNING_MESSAGE = "No payment modules have been installed.";
	
	private SearchResultPage searchResultPage;
	@BeforeMethod(description = "User logs in to application and searches for a product")
	public void setUp() {
		searchResultPage = homePage.goToLoginPage().doLoginWith("abc123@gmail.com", "password")
				.searchForProduct(SEARCH_TERM);
	}

	@Test(description = "Verify if logged in user able to buy dress", groups = { "e2e", "sanity" })
	public void checkoutTest() {
		assertEquals(
				searchResultPage.clickOnProductAtIndex(0).changeSizeTo(L).addProductToCart().proceedToCheckout()
						.goToConfirmAddresspage().goToShipmentPage().goToPaymentPage().verifyNoPaymentmethodsAlert(),
				EXPECTED_PAYMENT_WARNING_MESSAGE);
	}
}
