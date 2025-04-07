Feature: Validate Reqres API Endpoints

  @API @Reqres
  Scenario: GET request to Reqres API returns user list
    # Verify pageNumber = 2
    Given I send a GET request to the Reqres API to list users by pageNumber: 2
    Then I verify that the response code is 200
    And I verify that the last response contains data with key page and value 2
    And I verify that the last response contains data with key email and value michael.lawson@reqres.in
    And I verify that the last response contains data with key first_name and value Michael
    And I verify that the last response contains data with key last_name and value Lawson
    # Verify pageNumber = 1
    When I send a GET request to the Reqres API to list users by pageNumber: 1
    Then I verify that the response code is 200
    And I verify that the last response contains data with key page and value 1
    And I verify that the last response contains data with key email and value george.bluth@reqres.in
    And I verify that the last response contains data with key first_name and value George
    And I verify that the last response contains data with key last_name and value Bluth

  @API @Reqres
  Scenario: POST request to Reqres API to create user
    Given I send a POST request to the Reqres API to create user with body:
    """
    {
      "name": "morpheus",
      "job": "leader"
    }
    """
    Then I verify that the response code is 201
    And I verify that the last response contains key id
    And I verify that the last response contains key createdAt

  @API @Reqres
  Scenario: GET request to Reqres API returns single user
    Given I send a GET request to the Reqres API for userId: 2
    Then I verify that the response code is 200
    And I verify that the last response contains data with key email and value janet.weaver@reqres.in
    And I verify that the last response contains data with key first_name and value Janet
    And I verify that the last response contains data with key last_name and value Weaver

  @API @Reqres
  Scenario: DELETE request to Reqres API to delete user
    Given I send a DELETE request to the Reqres API for userId: 2
    Then I verify that the response code is 204