package stepDefinition.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReqresApiActions {

    private final String reqresApiUrl = "https://reqres.in/api/";
    private HttpResponse<String> response; // Holds the most recent HTTP response so it can be used in subsequent steps for assertions

    @Given("^I send a GET request to the Reqres API to list users by pageNumber: (.*)$")
    public void i_send_a_get_request_to_reqres_api_to_list_users_by_page_number(String pageNumber) {
        sendRequest(HttpMethod.GET, ReqresApiEndpoints.LIST_USERS.getEndpointUrl() + pageNumber, null);
    }

    @Given("^I send a POST request to the Reqres API to create user with body:$")
    public void i_send_a_post_request_to_reqres_api_to_create_user(String reqBody) {
        sendRequest(HttpMethod.POST, ReqresApiEndpoints.CREATE_USER.getEndpointUrl(), reqBody);
    }

    @Given("^I send a (GET|DELETE) request to the Reqres API for userId: (.*)$")
    public void i_send_a_request_to_reqres_api_for_user(HttpMethod httpMethod, String userId) {
        ReqresApiEndpoints endpoint;

        switch (httpMethod) {
            case GET:
                endpoint = ReqresApiEndpoints.GET_USER;
                break;
            case DELETE:
                endpoint = ReqresApiEndpoints.DELETE_USER;
                break;
            default:
                throw new IllegalArgumentException("Unsupported method: " + httpMethod);
        }

        sendRequest(httpMethod, endpoint.getEndpointUrl().replace("{id}", userId), null);
    }

    @Then("^I verify that the response code is (\\d+)$")
    public void i_verify_that_the_response_code_is(int expectedStatusCode) {
        Assert.assertEquals("Expected status code " + expectedStatusCode + ", but got " + response.statusCode(),
                expectedStatusCode, response.statusCode());
    }

    @Then("^I verify that the last response contains data with key (.*) and value (.*)$")
    public void i_verify_that_the_last_response_contains_data_with_key_and_value(String key, String value) {
        boolean isNumeric = value.matches("-?\\d+(\\.\\d+)?");

        // If the value is numeric, don't wrap it with double quotes.
        if (isNumeric) {
            value = value.replaceAll("\"", ""); // Remove quotes if it's numeric
        } else {
            value = "\"" + value + "\""; // Wrap in quotes if it's a string
        }

        Assert.assertTrue("Response body does not contain the expected key-value pair",
                response.body().contains("\"" + key + "\":" + value));
    }

    @Then("^I verify that the last response contains key (.*)$")
    public void i_verify_that_last_response_contains_key(String key) {
        Assert.assertTrue("Response body does not contain expected key: " + key,
                response.body().contains("\"" + key + "\""));
    }

    private void sendRequest(HttpMethod httpMethod, String endpointUrl, String reqBody) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(reqresApiUrl + endpointUrl))
                    .header("x-api-key", "reqres-free-v1");

            switch (httpMethod) {
                case GET:
                    requestBuilder.GET().build();
                    break;
                case POST:
                    requestBuilder.POST(HttpRequest.BodyPublishers.ofString(reqBody)).build();
                    break;
                case PUT:
                    requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(reqBody)).build();
                    break;
                case DELETE:
                    requestBuilder.DELETE().build();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + httpMethod);
            }

            response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

            System.out.println("Response: " + response.body());

        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            Assert.fail("Request failed due to exception: " + e.getMessage());
        }
    }
}
