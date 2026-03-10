package com.ui.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.tests.ProductDetailPage;
import com.utility.BrowserUtility;

public class SearchResultPage extends BrowserUtility {
	private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class=\"lighter\"]");
	private static final By ALL_PRODUCT_NAMES_LOCATOR = By.xpath("//h5[@itemprop=\"name\"]/a");

	public SearchResultPage(WebDriver webDriver) {
		super(webDriver);

	}

	public String getSearchResultTitle() {
		return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
	}

	public boolean getAllProductNames(String searchTerm) {
		List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
		List<String> productNamesList = getAllVisibleText(ALL_PRODUCT_NAMES_LOCATOR);
		System.out.println("products from website " + productNamesList);
		System.out.println("keywords " + keywords);
		boolean result = productNamesList.stream()
				.anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));

		return result;
	}

	public ProductDetailPage clickOnProductAtIndex(int index) {

		clickOn(getAllElements(ALL_PRODUCT_NAMES_LOCATOR).get(index));

		return new ProductDetailPage(getDriver());
	}

}
