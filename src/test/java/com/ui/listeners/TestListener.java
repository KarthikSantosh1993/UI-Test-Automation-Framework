package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger log = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onStart(ITestContext context) {
		ExtentReporterUtility.setUpSparkReporter("report.html");
		
		log.info("Test Suite started");
	}

	public void onTestStart(ITestResult result) {
		//extentTest = extentReports.createTest(result.getMethod().getMethodName());

		log.info(result.getMethod().getMethodName());
		log.info(result.getMethod().getDescription());
		log.info(Arrays.toString(result.getMethod().getGroups()));
		
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " " + "PASSED");
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		log.error(result.getMethod().getMethodName() + " " + "FAILED");
		log.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		
		Object testclass= result.getInstance();
		BrowserUtility browserUtility= ((TestBase)testclass).getInstance();
		log.info("Capturing screenshot for the failed test");
		String screenshotpath= browserUtility.takeScreenshot(result.getMethod().getMethodName());
		log.info("Attaching screenshot to the HTML report");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotpath);
	}

	public void onTestSkipped(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
	}

	public void onFinish(ITestContext context) {
		log.info("Test Suite completed");
		ExtentReporterUtility.flushReport();

	}
}
