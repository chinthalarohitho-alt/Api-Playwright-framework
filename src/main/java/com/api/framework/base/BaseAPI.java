package com.api.framework.base;

import com.api.framework.config.ScenarioContext;
import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

public class BaseAPI {

    public void createRequestContext(ScenarioContext context, String baseUrl) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setBaseURL(baseUrl)
                .setExtraHTTPHeaders(headers)
        );
        
        APIRequestContext requestContext = browserContext.request();
        
        context.setPlaywright(playwright);
        context.setBrowser(browser);
        context.setBrowserContext(browserContext);
        context.setRequestContext(requestContext);
    }

    public void closeRequestContext(ScenarioContext context) {
        try {
            if (context.getRequestContext() != null) {
                context.getRequestContext().dispose();
            }
        } catch (Exception e) { /* Ignore */ }

        try {
            if (context.getBrowserContext() != null) {
                context.getBrowserContext().close();
            }
        } catch (Exception e) { /* Ignore */ }

        try {
            if (context.getBrowser() != null) {
                context.getBrowser().close();
            }
        } catch (Exception e) { /* Ignore */ }

        try {
            if (context.getPlaywright() != null) {
                context.getPlaywright().close();
            }
        } catch (Exception e) { /* Ignore */ }
    }
}
