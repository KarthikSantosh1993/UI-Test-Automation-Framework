package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest0 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();//loose coupling   //Browser session is created. Launches a browser window
		driver.get("https://automationpractice.techwithjatin.com/");
		driver.manage().window().maximize();
		
		By signInLinkLocator= By.xpath("//a[contains(text(),'Sign in')]");
		WebElement signInLinkElement= driver.findElement(signInLinkLocator);
		signInLinkElement.click();
		
		By emailTextBoxLocator = By.id("email");
		WebElement emailTextBoxElement= driver.findElement(emailTextBoxLocator);
		emailTextBoxElement.sendKeys("abc123@gmail.com");
		
		By passwordTextBoxLocator = By.id("passwd");
		WebElement passwordTextBoxElement= driver.findElement(passwordTextBoxLocator);
		passwordTextBoxElement.sendKeys("password");
		
		By submitButtonLocator = By.id("SubmitLogin");
		WebElement submitButtonElement= driver.findElement(submitButtonLocator);
		submitButtonElement.click();
		
		
	}
}
