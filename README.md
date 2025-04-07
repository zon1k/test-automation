# Test Automation Project
This project is an automation framework for testing API, Mobile, and UI applications using **Cucumber**, **Selenium**, and **Appium**.

## Technologies Used
* **Java 17:** The project uses Java 17 as the programming language

* **Maven:** The build tool used for dependency management and project structure

* **Selenium:** A framework for automating web browsers for UI testing

* **Cucumber:** A tool for Behavior-Driven Development (BDD), used for writing tests in Gherkin syntax

* **Appium:** A framework for automating mobile applications

* **WebDriverManager:** A library to manage web driver binaries for Selenium

* **Dotenv Java:** A Java library to load environment variables from a .env file

## Project structure
```test-automation/
├── src/test/java/
│   ├── runner/
│   │   └── TestRunner.java
│   ├── stepDefinition/
│   │   ├── api/
│   │   │   ├── HttpMethod.java
│   │   │   ├── ReqresApiActions.java
│   │   │   └── ReqresApiEndpoints.java
│   │   ├── mobile/
│   │   │   ├── CalculatorActions.java
│   │   │   └── CalculatorPage.java
│   │   └── ui/
│   │       ├── EnvConfigLoader.java
│   │       ├── EnvConfigValidator.java
│   │       ├── SauceDemoActions.java
│   │       ├── WebDriverUtils.java
│   │       ├── WebElementAction.java
│   │       └── WebElements.java
├── src/test/resources/features/
│   ├── api/
│   │   └── ReqresApi.feature
│   ├── mobile/
│   │   └── Calculator.feature
│   └── ui/
│       └── SauceDemoUI.feature
├── pom.xml
```

## Dependencies
### **1. Selenium**
* Version: 4.10.0
* Purpose: Selenium is used to automate browser interactions for UI testing

Maven Dependency:
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.10.0</version>
</dependency>
```

### **2. Cucumber**
* Version: 7.14.0
* Purpose: Cucumber is used for Behavior-Driven Development (BDD), allowing you to write test scenarios in Gherkin syntax. Cucumber-JUnit integrates Cucumber with JUnit for running tests.

Maven Dependencies:
```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.14.0</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>7.14.0</version>
</dependency>
```
### **3. WebDriverManager**
* Version: 5.8.0
* Purpose: Automatically manages the drivers required for Selenium (like ChromeDriver, GeckoDriver, etc.).

Maven Dependency:
```xml
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.8.0</version>
    <scope>test</scope>
</dependency>
```

### **4. Dotenv Java**
* Version: 2.2.4
* Purpose: This library is used for loading environment variables from a .env file. It allows you to store sensitive configuration data (e.g., API keys, database credentials) outside the codebase.

Maven Dependency:
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>2.2.4</version>
</dependency>
```

### **5. Appium Java Client**
* Version: 8.5.1
* Purpose: Appium is used for automating mobile applications. The Java client provides the necessary APIs to interact with Android and iOS apps.

Maven Dependency:
```xml
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>8.5.1</version>
</dependency>
```

## Features of the Project
### 1. UI Testing
* **Sauce Demo UI:** Automation for testing the Sauce Demo UI application.
* **Feature file:** src/test/resources/features/ui/SauceDemoUI.feature
* **Step Definitions:** Located under src/test/java/stepDefinition/ui/.

### 2. API Testing
* **Reqres API:** Tests are written for the Reqres API, a simple API for testing.
* **Feature file:** src/test/resources/features/api/ReqresApi.feature
* **Step Definitions:** Located under src/test/java/stepDefinition/api/.

### 3. Mobile Testing
* **Calculator App:** Automation for testing a mobile calculator app.
* **Feature file:** src/test/resources/features/mobile/Calculator.feature
* **Step Definitions:** Located under src/test/java/stepDefinition/mobile/.


## How to Run the Project
### Prerequisites
* Install **Java 17**
* Install **Maven**
* Install **Appium** for mobile testing

## Steps 
### **1. Clone the repository:**
```bash
git clone https://github.com/yourusername/test-automation.git
```
### **2. Navigate to the Repository:**
```bash
cd test-automation
```
### **3. Install dependencies using Maven:**
```bash
mvn clean install
```
### **4. Run a Specific Feature File from Command Line**<br>
**Example for UI Testing:**<br>
To run the **SauceDemoUI.feature** file, use the following command:
```bash
mvn test "-Dcucumber.features=src/test/resources/features/ui/SauceDemoUI.feature"
```

**Example for API Testing:**<br>
To run the **ReqresApi.feature** file, use the following command:
```bash
mvn test "-Dcucumber.features=src/test/resources/features/api/ReqresApi.feature"
```

**Example for Mobile Testing:**<br>
**Before running the Mobile tests, ensure that the Appium server is running. You can start the Appium server by running the following command in your terminal:**
```bash
appium
```
To run the **Calculator.feature** file, use the following command:
```bash
mvn test "-Dcucumber.features=src/test/resources/features/mobile/Calculator.feature"
```

## Notes:
* Make sure you have the correct environment set up for running Appium and Selenium tests.
* For mobile testing, you will need Android Studio or Xcode (for iOS) and the necessary drivers installed on your system.
* Ensure the necessary environment variables are set:
* * `SAUCE_USERNAME` and `SAUCE_PASSWORD` for Sauce Labs, which should be stored in your .env file.
## Troubleshooting:
If you are using an emulator from Android Studio, please use a Google Pixel device. Nexus emulators do not include a built-in Calculator app.<br>
If you are using **Appium v2**, you need to manually install the Android driver:<br>
```bash
appium driver install uiautomator2
```
Ensure that `ANDROID_HOME` is correctly set in your system environment variables.