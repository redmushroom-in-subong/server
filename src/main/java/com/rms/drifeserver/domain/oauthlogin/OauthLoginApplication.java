package com.rms.drifeserver.domain.oauthlogin;

import com.rms.drifeserver.domain.oauthlogin.config.properties.AppProperties;
import com.rms.drifeserver.domain.oauthlogin.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@SpringBootApplication
//@EnableConfigurationProperties({
//        CorsProperties.class,
//        AppProperties.class
//})
public class OauthLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthLoginApplication.class, args);
    }

}
