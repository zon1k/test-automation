Feature: Validate SauceDemo UI

  @UI @SauceDemo
  Scenario: Happy Path - Successful login, shopping, and checkout flow with valid credentials
    # Verify that elements are loaded correctly on the login page
    Given I verify that elements are present on the SauceDemo login page

    # Login with valid credentials
    When I enter valid credentials from the environment file and click on login button
    Then I should see the homepage with the title Swag Labs

    # Validate that the product sorting options are available and correct
    Then I verify that the filter contains
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

    # Add items to the cart
    Then I add item Sauce Labs Backpack to the cart
    Then I add item Sauce Labs Bike Light to the cart
    Then I add item Sauce Labs T-Shirt to the cart
    Then I add item Sauce Labs Fleece Jacket to the cart
    Then I add item Sauce Labs Onesie to the cart

    # Verify cart contains 5 items after adding
    And I click on the Shopping cart button
    Then the cart should contain 5 items

    # Remove 3 items from the cart
    Then I remove item Sauce Labs Onesie from the cart
    Then I remove item Sauce Labs T-Shirt from the cart
    Then I remove item Sauce Labs Bike Light from the cart

    # Verify cart contains 2 items after removal
    Then the cart should contain 2 items

    # Proceed to checkout
    And I click on the Checkout button
    Then I fill out the checkout form with first name David, last name Schvrz and postal code 12345
    And I click on the Continue button

    # Verify the checkout overview page is correct
    Then I verify the checkout overview page

    # Complete the checkout process
    And I click on the Finish button
    Then I verify the checkout complete page

    # Logout from the application
    Then I open the menu and select Logout