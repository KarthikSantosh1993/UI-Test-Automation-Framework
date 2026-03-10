package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginInValidTest extends TestBase {

	private static final String INVALID_EMAIL_ADDRESS = "krthiksantosh@gmail.com";
	private static final String INVALID_PASSWORD = "qwer1234";

	@Test(description = "Verify if proper error message is shown for invalid credentials when user enter invalid credentails ", groups = {
			"e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataprovider.class, dataProvider = "LoginDataProvider")
	public void loginInvalidCredentialsTest(User user) {
		assertEquals(homePage.goToLoginPage()
				.doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(),
				"Authentication failed.");
	}

}
