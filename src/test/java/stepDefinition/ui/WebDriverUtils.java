package stepDefinition.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtils {

    /**
     * Method to wait for a condition and then perform the corresponding action on the element.
     *
     * @param waitTimeInSeconds Time to wait for the condition.
     * @param driver            WebDriver instance.
     * @param element           The WebElement to be checked.
     * @param action            The action to be performed.
     */
    public static void wait(int waitTimeInSeconds, WebDriver driver, WebElement element, WebElementAction action) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));

        switch (action) {
            case CLICK:
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            case CLICKABLE:
                wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until clickable
                break;
            case DISPLAYED:
                wait.until(ExpectedConditions.visibilityOf(element)); // Wait until visible
                break;
            case PRESENT:
                wait.until(ExpectedConditions.presenceOfElementLocated((By) element)); // Wait until present
                break;
            default:
                throw new IllegalArgumentException("Invalid action specified.");
        }
    }
}
