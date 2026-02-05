package com.api.framework.StepsMethods;

import com.api.framework.config.ScenarioContext;
import com.api.framework.utils.APIUtils;
import com.microsoft.playwright.APIResponse;

/**
 * API wrapper class for Pet Store API endpoints.
 */
public class PetAPI {
    
    private final ScenarioContext context;

    public PetAPI(ScenarioContext context) {
      this.context = context;
    }

    // --- API Operations ---

    public APIResponse get(String endpoint) {
        return APIUtils.get(context.getRequestContext(), endpoint);
    }

    public APIResponse post(String endpoint, Object payload) {
        return APIUtils.post(context.getRequestContext(), endpoint, payload);
    }

    public APIResponse put(String endpoint, Object payload) {
        return APIUtils.put(context.getRequestContext(), endpoint, payload);
    }

    public APIResponse delete(String endpoint) {
        return APIUtils.delete(context.getRequestContext(), endpoint);
    }

    // --- Validations ---

    public void validateString(APIResponse response, String expectedName) {
        APIUtils.validateString(response, "$.name", expectedName);
    }

    public void validateStatusCode(APIResponse response, int expectedStatusCode) {
        APIUtils.validateStatusCode(response, expectedStatusCode);
    }
}
