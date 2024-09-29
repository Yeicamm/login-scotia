package com.login.scotia.config.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginScotiaProperties {

    @Value("${keycloak.auth-server-integration}")
    private String keycloakUrl;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.CLIENT.SECRET}")
    private String clientSecret;
}
