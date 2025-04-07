package stepDefinition.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinition.ui.WebElementAction.DISPLAYED;
import static stepDefinition.ui.WebElementAction.CLICK;

public class SauceDemoActions {

    private WebDriver driver;
    private WebElements webElements;

    @Before("@UI")
    public void setUp() {
        // Validate .env file before running the tests
        try {
            boolean isEnvFileValid = EnvFileValidator.isEnvFileValid();
            Assert.assertTrue("The .env file does not contain valid SAUCE_USERNAME or SAUCE_PASSWORD. Please verify that both are correctly set.", isEnvFileValid);
        } catch (Exception e) {
            Assert.fail("An error occurred while reading the .env file: " + e.getMessage() + ". Please ensure that SAUCE_USERNAME and SAUCE_PASSWORD are set properly in the .env file.");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Initialize WebElements
        webElements = new WebElements(driver);
    }

    @After("@UI")
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // This will close the browser and end the session
        }
    }

    @Given("^I verify that elements are present on the SauceDemo login page$")
    public void i_verify_that_elements_are_present() {
        WebDriverUtils.wait(10, driver, webElements.usernameField, DISPLAYED);
        WebDriverUtils.wait(10, driver, webElements.passwordField, DISPLAYED);
        WebDriverUtils.wait(10, driver, webElements.loginButton, DISPLAYED);
    }

    @When("^I enter valid credentials from the environment file and click on login button$")
    public void i_enter_valid_credentials_from_the_environment_file_and_click_on_login_button() {
        String username = EnvConfigLoader.getUsername();
        String password = EnvConfigLoader.getPassword();

        webElements.usernameField.sendKeys(username);
        webElements.passwordField.sendKeys(password);
        WebDriverUtils.wait(10, driver, webElements.loginButton, CLICK);
    }

    @Then("^I should see the homepage with the title (.*)$")
    public void i_should_see_homepage_with_title(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Then("^I verify that the filter contains$")
    public void i_verify_that_the_filter_contains(List<String> expectedOptions) {
        List<WebElement> allOptions = new Select(webElements.productSortDropdown).getOptions();

        for (String expectedOption : expectedOptions) {
            boolean optionFound = false;

            for (WebElement option : allOptions) {
                if (option.getText().equals(expectedOption)) {
                    optionFound = true;
                    break;
                }
            }

            // Assert the expected option was found in the dropdown
            assertTrue("Option '" + expectedOption + "' was not found in the dropdown", optionFound);
        }
    }

    @Then("^I add item Sauce Labs (Backpack|Bike Light|T-Shirt|Fleece Jacket|Onesie) to the cart$")
    public void i_add_item_to_the_cart(String itemName) {
        switch (itemName) {
            case "Backpack":
                WebDriverUtils.wait(10, driver, webElements.addToCartSauceLabsBackpack, CLICK);
                break;
            case "Bike Light":
                WebDriverUtils.wait(10, driver, webElements.addToCartSauceLabsBikeLight, CLICK);
                break;
            case "T-Shirt":
                WebDriverUtils.wait(10, driver, webElements.addToCartSauceLabsBoltTShirt, CLICK);
                break;
            case "Fleece Jacket":
                WebDriverUtils.wait(10, driver, webElements.addToCartSauceLabsFleeceJacket, CLICK);
                break;
            case "Onesie":
                WebDriverUtils.wait(10, driver, webElements.addToCartSauceLabsOnesie, CLICK);
                break;
            default:
                throw new IllegalArgumentException("Item not recognized: " + itemName);
        }
    }

    @Then("^I click on the (Shopping cart|Checkout|Continue|Finish) button$")
    public void i_click_on_the_shopping_cart_icon(String button) {
        switch (button) {
            case "Shopping cart":
                WebDriverUtils.wait(10, driver, webElements.buttonShoppingCart, CLICK);
                break;
            case "Checkout":
                WebDriverUtils.wait(10, driver, webElements.buttonCheckout, CLICK);
                break;
            case "Continue":
                WebDriverUtils.wait(10, driver, webElements.buttonContinue, CLICK);
                break;
            case "Finish":
                WebDriverUtils.wait(10, driver, webElements.buttonFinish, CLICK);
                break;
            default:
                throw new IllegalArgumentException("Button not recognized: " + button);
        }
    }

    @Then("^the cart should contain (\\d+) item[s]?$")
    public void the_cart_should_contain_item(int expectedCount) {
        WebDriverUtils.wait(10, driver, webElements.shoppingCartBadge, DISPLAYED);
        Assert.assertEquals(
                "Cart item count does not match",
                expectedCount,
                Integer.parseInt(webElements.shoppingCartBadge.getText())
        );
    }

    @Then("^I remove item Sauce Labs (Backpack|Bike Light|T-Shirt|Fleece Jacket|Onesie) from the cart$")
    public void i_remove_item_from_the_cart(String itemName) {
        switch (itemName) {
            case "Backpack":
                WebDriverUtils.wait(10, driver, webElements.removeFromCartSauceLabsBackpack, CLICK);
                break;
            case "Bike Light":
                WebDriverUtils.wait(10, driver, webElements.removeFromCartSauceLabsBikeLight, CLICK);
                break;
            case "T-Shirt":
                WebDriverUtils.wait(10, driver, webElements.removeFromCartSauceLabsBoltTShirt, CLICK);
                break;
            case "Fleece Jacket":
                WebDriverUtils.wait(10, driver, webElements.removeFromCartSauceLabsFleeceJacket, CLICK);
                break;
            case "Onesie":
                WebDriverUtils.wait(10, driver, webElements.removeFromCartSauceLabsOnesie, CLICK);
                break;
            default:
                throw new IllegalArgumentException("Item not recognized: " + itemName);
        }
    }

    @Then("^I fill out the checkout form with first name (.*), last name (.*) and postal code (.*)$")
    public void i_fill_out_checkout_form(String firstName, String lastName, String postalCode) {
        WebDriverUtils.wait(10, driver, webElements.checkoutFirstName, DISPLAYED);
        webElements.checkoutFirstName.sendKeys(firstName);
        webElements.checkoutLastName.sendKeys(lastName);
        webElements.checkoutPostalCode.sendKeys(postalCode);
    }

    @Then("^I verify the checkout overview page$")
    public void i_verify_checkout_overview() {
        // Verify Payment Information
        WebDriverUtils.wait(10, driver, webElements.paymentInfoLabel, DISPLAYED);
        assertEquals("Payment Information:", webElements.paymentInfoLabel.getText());
        assertEquals("SauceCard #31337", webElements.paymentInfoValue.getText());

        // Verify Shipping Information
        WebDriverUtils.wait(10, driver, webElements.shippingInfoLabel, DISPLAYED);
        assertEquals("Shipping Information:", webElements.shippingInfoLabel.getText());
        assertEquals("Free Pony Express Delivery!", webElements.shippingInfoValue.getText());

        // Verify Price Total Information
        WebDriverUtils.wait(10, driver, webElements.totalInfoLabel, DISPLAYED);
        assertEquals("Price Total", webElements.totalInfoLabel.getText());
        assertEquals("Item total: $79.98", webElements.subtotalLabel.getText());
        assertEquals("Tax: $6.40", webElements.taxLabel.getText());
        assertEquals("Total: $86.38", webElements.totalLabel.getText());
    }

    @Then("^I verify the checkout complete page$")
    public void i_verify_checkout_complete_page() {
        // Verify the "Thank You" message
        WebDriverUtils.wait(10, driver, webElements.completeHeader, DISPLAYED);
        assertEquals("Thank you for your order!", webElements.completeHeader.getText());

        // Verify the order completion text
        WebDriverUtils.wait(10, driver, webElements.completeText, DISPLAYED);
        assertTrue(webElements.completeText.getText().contains("Your order has been dispatched"));
    }

    @When("^I open the menu and select (All Items|About|Logout|Reset App State)$")
    public void i_open_the_menu_and_select_option(String menuOption) {
        WebDriverUtils.wait(10, driver, webElements.burgerMenuButton, CLICK);

        switch (menuOption) {
            case "All Items":
                WebDriverUtils.wait(10, driver, webElements.buttonMenuAllItems, CLICK);
                break;
            case "About":
                WebDriverUtils.wait(10, driver, webElements.buttonMenuAbout, CLICK);
                break;
            case "Logout":
                WebDriverUtils.wait(10, driver, webElements.buttonMenuLogout, CLICK);
                break;
            case "Reset App State":
                WebDriverUtils.wait(10, driver, webElements.buttonMenuResetApp, CLICK);
                break;
            default:
                throw new IllegalArgumentException("Menu option not recognized: " + menuOption);
        }
    }

}
