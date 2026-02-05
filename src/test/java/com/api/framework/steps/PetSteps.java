package com.api.framework.steps;

import com.api.framework.StepsMethods.PetAPI;
import com.api.framework.config.ScenarioContext;

import com.api.framework.utils.APIUtils;
import io.cucumber.java.en.*;

public class PetSteps {

    private final PetAPI petAPI;
    private final ScenarioContext context;
    public Object payload;

    public PetSteps(ScenarioContext context) {
        this.petAPI = new PetAPI(context);
        this.context = context;
    }

    @Given("get the scheme {string}")
    public void get_the_scheme(String payloadKeyword) {
        payload = APIUtils.getPayload(payloadKeyword);
    }

    @When("post request send with endpoint {string}")
    public void postRequestSendWithEndpointAndPayload(String endpoint) {
        context.setResponse(petAPI.post(endpoint, payload));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        APIUtils.validateStatusCode(context.getResponse(), statusCode);
    }

    @Then("the json path {string} should be {string}")
    public void theJsonPathShouldBe(String path, String expectedValue) {
        APIUtils.validateString(context.getResponse(), "$." + path, expectedValue);
    }

    @When("post request send with endpoint {string} and with the payload {string}")
    public void post_request_send_with_endpoint_and_with_the_payload(String s, String s2) {
        context.setResponse(petAPI.post(s, s2));
    }

    @Given("update the json path {string} with value {string}")
    public void update_the_json_path_with_value(String path, String value) {
        payload = APIUtils.updateTheJsonValue(payload,path,value);
    }

 
}
