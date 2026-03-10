package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

public class AddNewAddressTest extends TestBase {
	private MyAccountPage myAccountPage;
	private AddressPOJO address;
	private AddressPage addressPage;

	@BeforeMethod(description = "valid first time user is logged in to the application")
	public void setUp() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("createAddress@automation.com", "password");
		address = FakeAddressUtility.getFakeAddress();
	}

	@Test
	public void addNewFirstAddress() {
		addressPage = myAccountPage.goToAddAddressPage();
		Assert.assertEquals(addressPage.saveAddress(address), address.getAddressAlias().toUpperCase());
	}

	@AfterMethod(description = "delete account after account creation")
	public void tearDown() {
		if (addressPage != null) {
			addressPage.deleteAccount();
		}
	}
}
