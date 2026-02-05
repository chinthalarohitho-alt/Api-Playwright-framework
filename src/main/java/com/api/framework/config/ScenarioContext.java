package com.api.framework.config;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;

public class ScenarioContext {
    private APIResponse response;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private APIRequestContext requestContext;

    public APIResponse getResponse() {
        return response;
    }

    public void setResponse(APIResponse response) {
        this.response = response;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public void setPlaywright(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public void setBrowserContext(BrowserContext browserContext) {
        this.browserContext = browserContext;
    }

    public APIRequestContext getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(APIRequestContext requestContext) {
        this.requestContext = requestContext;
    }
}
