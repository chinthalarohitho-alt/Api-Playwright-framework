package com.api.framework.hooks;

import com.api.framework.base.BaseAPI;
import com.api.framework.config.ConfigReader;
import com.api.framework.config.ScenarioContext;
import com.api.framework.config.Settings;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class FrameworkHooks extends BaseAPI {

    private final ScenarioContext context;
    private static boolean resultsCleaned = false;

    public FrameworkHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        if (!resultsCleaned) {
            deleteDirectory(new java.io.File("allure-results"));
            resultsCleaned = true;
        }
        ConfigReader.PopulateSettings();
        createRequestContext(context, Settings.Url);
        
        // Start Playwright Tracing
        context.getBrowserContext().tracing().start(new com.microsoft.playwright.Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    private void deleteDirectory(java.io.File directoryToBeDeleted) {
        if (!directoryToBeDeleted.exists()) return;
        java.io.File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (java.io.File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Only stop and save to zip if failed
            String tracePath = "allure-results/trace-" + System.currentTimeMillis() + ".zip";
            
            try {
                context.getBrowserContext().tracing().stop(new com.microsoft.playwright.Tracing.StopOptions()
                        .setPath(java.nio.file.Paths.get(tracePath)));
                
                byte[] traceContent = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(tracePath));
                io.qameta.allure.Allure.addAttachment("Playwright Trace", "application/zip", new java.io.ByteArrayInputStream(traceContent), ".zip");
            } catch (Exception e) {
                System.err.println("Failed to capture/attach trace: " + e.getMessage());
                try { context.getBrowserContext().tracing().stop(); } catch (Exception ignored) {}
            }
        } else {
            // For passed tests, just stop tracing without saving
            try {
                context.getBrowserContext().tracing().stop();
            } catch (Exception ignored) {}
        }
        
        closeRequestContext(context);
    }
}
