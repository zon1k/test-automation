package stepDefinition.api;

public enum ReqresApiEndpoints {
    LIST_USERS("users?page="),
    CREATE_USER("users"),
    GET_USER("users/{id}"),
    UPDATE_USER("users/{id}"),
    DELETE_USER("users/{id}"),
    REGISTER_USER("register"),
    LOGIN_USER("login");

    private String endpointUrl;

    ReqresApiEndpoints(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }
}
