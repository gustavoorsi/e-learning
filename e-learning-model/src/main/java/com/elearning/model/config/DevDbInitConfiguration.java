package com.elearning.model.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories // tell spring to enable Spring Data and its repository features such as an Entity Manager, DataSource, etc.
 @Profile("development")
public class DevDbInitConfiguration {

}
