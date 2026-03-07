package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;

public class LoginTest4 {

	public static void main(String[] args) {
		HomePage homePage = new HomePage(Browser.CHROME, true);
		String username = homePage.goToLoginPage().doLoginWith("abc123@gmail.com", "password").getUserName();
		System.out.println(username);
	}
}
