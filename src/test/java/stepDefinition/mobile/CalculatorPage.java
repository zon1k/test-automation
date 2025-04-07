package stepDefinition.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CalculatorPage {

    private final AndroidDriver driver;

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private WebElement getDigitButton(int digit) {
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }

    private WebElement getPlusButton() {
        return driver.findElement(By.id("com.android.calculator2:id/op_add"));
    }

    private WebElement getDivideButton() {
        return driver.findElement(By.id("com.android.calculator2:id/op_div"));
    }

    private WebElement getEqualsButton() {
        return driver.findElement(By.id("com.android.calculator2:id/eq"));
    }

    private WebElement getResultField() {
        return driver.findElement(By.id("com.android.calculator2:id/result"));
    }

    public void performAddition(int a, int b) {
        inputNumber(a);
        getPlusButton().click();
        inputNumber(b);
        getEqualsButton().click();
    }

    public void performDivision(int a, int b) {
        inputNumber(a);
        getDivideButton().click();
        inputNumber(b);
        getEqualsButton().click();
    }

    public String getResult() {
        return getResultField().getText();
    }

    private void inputNumber(int number) {
        for (char digit : String.valueOf(number).toCharArray()) {
            getDigitButton(Character.getNumericValue(digit)).click();
        }
    }
}
