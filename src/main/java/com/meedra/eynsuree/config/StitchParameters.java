package com.meedra.eynsuree.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "stitch")
@Data
public class StitchParameters {

        private String graphqlServer;
        private String tokenUrl;
        private String grantType;
        private String audience;
        private String clientAssertionType;
}
