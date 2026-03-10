package com.utility;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

public class FakeAddressUtility {

	public static AddressPOJO getFakeAddress() {
		Faker faker = new Faker(Locale.US);
		int randomNum = faker.number().numberBetween(1, 51);
		String state = String.valueOf(randomNum);
		return new AddressPOJO(faker.company().name(), faker.address().buildingNumber(),
				faker.address().streetAddress(), faker.address().city(), faker.numerify("#####"),
				faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(), "other", "office address", state);
	}
}
