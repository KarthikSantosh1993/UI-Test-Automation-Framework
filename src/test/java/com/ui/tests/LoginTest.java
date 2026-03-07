package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{

//	@Test(description = "Verify if valid user is able to login", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataprovider.class, dataProvider = "LoginDataProvider")
//	public void loginTest(User user) {
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Karthik Duruvasula");
//	}
//
	@Test(description = "Login test with CSV Test data", groups = {
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataprovider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCSVTest(User user) {
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Karthik Duruvasula");
	}

//	@Test(description = "Login test with Excel Test data", groups = {
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataprovider.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
//	public void loginExcelTest(User user) {
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Karthik Duruvasula");
//	}
	
	
	
}
