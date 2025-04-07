package stepDefinition.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorActions {

    public AndroidDriver driver;
    public CalculatorPage calcPage;

    @Before("@Mobile")
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");  // Replace with your emulator/device name
        capabilities.setCapability("appPackage", "com.android.calculator2");  // The package name of the Calculator app
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");  // The activity of the Calculator app
        capabilities.setCapability("automationName", "UiAutomator2");  // Use UiAutomator2 for Android automation

        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);

        // Initialize page object AFTER driver is created
        calcPage = new CalculatorPage(driver);
    }

    @After("@Mobile")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Calculator is open")
    public void calculator_is_open() {
        // Try to find an element that is only present when the Calculator app is open
        WebElement element = driver.findElement(By.id("com.android.calculator2:id/digit_1"));

        // Assert that the element is present, confirming that the Calculator app is open
        Assert.assertNotNull("Calculator app is not open", element);    }

    @When("I add {int} and {int}")
    public void i_add_numbers(int a, int b) {
        calcPage.performAddition(a, b);
    }

    @Then("I should see result {int}")
    public void i_should_see_result(int expected) {
        Assert.assertEquals(String.valueOf(expected), calcPage.getResult());
    }

    @When("I divide {int} by {int}")
    public void i_divide_by_zero(int a, int b) {
        calcPage.performDivision(a, b);
    }

    @Then("I should see an error or undefined result")
    public void i_should_see_error() {
        Assert.assertTrue(calcPage.getResult().contains("error") || calcPage.getResult().contains("∞"));

    }
}