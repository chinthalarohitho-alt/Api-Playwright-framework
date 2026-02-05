package com.api.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.api.framework.steps", "com.api.framework.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/Cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @org.testng.annotations.AfterSuite
    public void afterSuite() {
        System.out.println("\n\n=================================================================================");
        System.out.println("Cucumber Report: " + "file://" + System.getProperty("user.dir") + "/target/cucumber-reports/cucumber-pretty.html");
        System.out.println("Allure Report:   " + "file://" + System.getProperty("user.dir") + "/target/site/allure-maven-plugin/index.html");
        System.out.println("To view Allure with a server, run: mvn allure:serve");
        System.out.println("=================================================================================\n");
    }
}
