package com.ui.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.pages.MyAccountPage;

@Listeners(com.ui.listeners.TestListener.class)
public class SearchProductTest extends TestBase {
	private MyAccountPage myAccountPage;
	private static final String SEARCH_TERM = "Printed Summer Dress";

	@BeforeMethod(description = "valid user is logged in to the application")
	public void setUp() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("abc123@gmail.com", "password");
	}

	@Test(description = "Verify logged in user is able to search for product and correct products are displayed", groups = {
			"e2e", "sanity" })
	public void verifyProductSearchTest() {
		assertEquals(myAccountPage.searchForProduct(SEARCH_TERM).getAllProductNames(SEARCH_TERM), true);
	}
}
