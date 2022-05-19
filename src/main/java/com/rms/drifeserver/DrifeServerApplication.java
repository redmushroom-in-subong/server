package com.rms.drifeserver;

import com.rms.drifeserver.config.properties.AppProperties;
import com.rms.drifeserver.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class DrifeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrifeServerApplication.class, args);
	}

}
