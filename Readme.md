
# Java-Selenium UI Test Automation Framework
- A robust, scalable Selenium-based automation framework built with Java 11.

- It is designed to provide high-performance testing for web applocations across local and cloud environments.

## 🚀 Author

- [Name: Karthik Duruvasula](https://github.com/KarthikSantosh1993)
- [Email: ](krthiksantosh@gmail.com) krthiksantosh@gmail.com

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/KarthikSantosh1993)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/karthik-duruvasula-b6972974)



##  About Me
Hi, I am Karthik Duruvasula. I have 7+ years of experience in Automation testing using technologies like Selenium WebDriver, RestAssured.

My major expertise is in Java programming language.


## 🛠 Pre-requisites
Before running the tests, ensure you have the following installed:

**Java JDK:** 11

**Apache Maven:** 3.x
- Download Link https://maven.apache.org/download.cgi

**Browsers:** Chrome/Firefox/Safari (for local runs)

**LambdaTest Credentials:** (Username and Access Key) if running on the cloud

## 🛠 Technologies & Libraries

| Category | Technology | Description |
| :--- | :--- | :--- |
| **Core Framework** | **Java 11** | Primary programming language utilizing modern syntax and features. |
| | **Selenium WebDriver** | Core engine for browser automation and DOM interaction. |
| | **TestNG** | Testing framework for execution control, assertions, retry for flaky tests, listeners and parallelization. |
| | **Maven** | Dependency management and build lifecycle control. |
| **Data-Driven Testing** | **Apache POI** | Library for reading and writing data from Microsoft Excel (`.xlsx`) files. |
| | **GSON (Google JSON)** | Maps JSON data files into Java objects (POJOs) for flexible data input. |
| | **OpenCSV** | Lightweight library for handling CSV-based data-driven testing. |
| | **Java Faker** | Generates realistic, dynamic data (e.g., Addresses) for unique test runs. |
| **Infrastructure** | **LambdaTest** | Cloud-based grid provider for scalable cross-browser execution. |
| | **Maven Surefire** | Plugin to handle CLI parameters (`browser`, `isHeadless`) and trigger suites. |
| **Reporting & Logs** | **Extent Reports** | Generates detailed, interactive HTML dashboards for test results. |
| | **Log4j** | Robust logging framework for tracking technical execution details. |

## 📂 Project Structure

| Directory / File | Description |
| :--- | :--- |
| **src/test/java** | Test automation components and test scripts. |
| **com.constants** | Enums for Browser types, Environments, and Window sizes. |
| **com.ui.dataproviders**| Data-driven logic via TestNG DataProviders. |
| **com.ui.listeners** | Reporting listeners and test retry analyzers. |
| **com.ui.pages** | Page Object Model (POM) implementation. |
| **com.ui.pojo** | Java objects for data mapping (JSON/Excel). |
| **com.utility** | Utility classes for File I/O, Logging, and WebDriver helpers. |
| **testData/** | Repository for CSV, JSON, and Excel test data files. |
| **screenshots/** | screenshots for failed tests. |
| **logs/** | Execution history and debugging logs (Log4j2). |
| **report.html** | Visual test execution report (Extent Reports). |
| **pom.xml** | Project dependencies and build configuration. |


## 🛠 Installation

**Clone Repository:**

```bash
git clone https://github.com/KarthikSantosh1993/UI-Test-Automation-Framework.git

cd UI-Test-Automation-Framework
```
**Run Locally (Chrome - Headed):**
```bash
mvn clean test -X -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=false
```
**Run Locally (Firefox - Headless):**
```bash
mvn clean test -X -Dbrowser=firefox -DisLambdaTest=false -DisHeadless=true
 ```   

 **Run on LambdaTest Cloud:**
```bash
mvn clean test -X -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false
 ``` 
### 🚀 Execution Guide
This framework uses the Maven Surefire Plugin to handle command-line arguments. Use the table below to configure your test run:

| Parameter | Description | Supported Values |
| :--- | :--- | :--- |
| `browser` | Defines the target browser | `chrome`, `firefox`, `edge`, `safari` |
| `isLambdaTest` | Toggle b/w Cloud and Local | `true`, `false` |
| `isHeadless` | Toggle Headless mode | `true`, `false` |

## 📈 Extent Reports
After every execution, a visual HTML report is generated at the root level:

**File: ./reports.html**

**Content:** Step-by-step pass/fail status, execution time, and screenshots for failed tests

## 📜 Logging
For deep technical debugging, check the logs/ directory:

**File: logs/application.log**

**Content:** Console output, element locator details, and data-loading events managed by Log4j.

## 📦 Hosting Execution Reports using gh-pages in Github
Automation Framework is integrated with github actions.

The Reports will be archived in gh-pages branch 
You can view HTML reports at: https://karthiksantosh1993.github.io/UI-Test-Automation-Framework/
