package com.api.framework.config;

import org.aeonbits.owner.Config;

@Config.Sources({
    "file:${user.dir}/src/main/java/com/api/framework/resources/config.properties"
})
public interface EnvironmentConfig extends Config {

    @Key("petstore.baseUrl")
    String Url();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("chatGptUrl")
    String chatGptUrl();

    @Key("geminiAppUrl")
    String geminiAppUrl();
}
