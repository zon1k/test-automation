package stepDefinition.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebElements {

    public WebElements(WebDriver driver) {
        // Initialize all elements in the WebElements class
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[data-test='username']")
    public WebElement usernameField;

    @FindBy(css = "input[data-test='password']")
    public WebElement passwordField;

    @FindBy(css = "input[data-test='login-button']")
    public WebElement loginButton;

    @FindBy(css = "a[data-test='shopping-cart-link']")
    public WebElement buttonShoppingCart;

    @FindBy(css = "span[data-test='shopping-cart-badge']")
    public WebElement shoppingCartBadge;

    @FindBy(css = "span[data-test='title']")
    public WebElement productsTitle;

    @FindBy(css = "select[data-test='product-sort-container']")
    public WebElement productSortDropdown;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-backpack']")
    public WebElement addToCartSauceLabsBackpack;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-bike-light']")
    public WebElement addToCartSauceLabsBikeLight;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")
    public WebElement addToCartSauceLabsBoltTShirt;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-fleece-jacket']")
    public WebElement addToCartSauceLabsFleeceJacket;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-onesie']")
    public WebElement addToCartSauceLabsOnesie;

    @FindBy(css = "button[data-test='remove-sauce-labs-backpack']")
    public WebElement removeFromCartSauceLabsBackpack;

    @FindBy(css = "button[data-test='remove-sauce-labs-bike-light']")
    public WebElement removeFromCartSauceLabsBikeLight;

    @FindBy(css = "button[data-test='remove-sauce-labs-bolt-t-shirt']")
    public WebElement removeFromCartSauceLabsBoltTShirt;

    @FindBy(css = "button[data-test='remove-sauce-labs-fleece-jacket']")
    public WebElement removeFromCartSauceLabsFleeceJacket;

    @FindBy(css = "button[data-test='remove-sauce-labs-onesie']")
    public WebElement removeFromCartSauceLabsOnesie;

    @FindBy(css = "button[data-test='checkout']")
    public WebElement buttonCheckout;

    @FindBy(css = "input[data-test='firstName']")
    public WebElement checkoutFirstName;

    @FindBy(css = "input[data-test='lastName']")
    public WebElement checkoutLastName;

    @FindBy(css = "input[data-test='postalCode']")
    public WebElement checkoutPostalCode;

    @FindBy(css = "input[data-test='continue']")
    public WebElement buttonContinue;

    @FindBy(css = "span[data-test='title']")
    public WebElement checkoutOverviewTitle;

    @FindBy(css = "button[data-test='finish']")
    public WebElement buttonFinish;

    @FindBy(css = "div[data-test='payment-info-label']")
    public WebElement paymentInfoLabel;

    @FindBy(css = "div[data-test='payment-info-value']")
    public WebElement paymentInfoValue;

    @FindBy(css = "div[data-test='shipping-info-label']")
    public WebElement shippingInfoLabel;

    @FindBy(css = "div[data-test='shipping-info-value']")
    public WebElement shippingInfoValue;

    @FindBy(css = "div[data-test='total-info-label']")
    public WebElement totalInfoLabel;

    @FindBy(css = "div[data-test='subtotal-label']")
    public WebElement subtotalLabel;

    @FindBy(css = "div[data-test='tax-label']")
    public WebElement taxLabel;

    @FindBy(css = "div[data-test='total-label']")
    public WebElement totalLabel;

    @FindBy(css = "h2[data-test='complete-header']")
    public WebElement completeHeader;

    @FindBy(css = "div[data-test='complete-text']")
    public WebElement completeText;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(css = "a[data-test='inventory-sidebar-link']")
    public WebElement buttonMenuAllItems;

    @FindBy(css = "a[data-test='about-sidebar-link']")
    public WebElement buttonMenuAbout;

    @FindBy(css = "a[data-test='logout-sidebar-link']")
    public WebElement buttonMenuLogout;

    @FindBy(css = "a[data-test='reset-sidebar-link']")
    public WebElement buttonMenuResetApp;
}
