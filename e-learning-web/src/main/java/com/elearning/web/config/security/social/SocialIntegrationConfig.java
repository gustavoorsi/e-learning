package com.elearning.web.config.security.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

//@formatter:off
/**
 * 
 * 
 * Spring Social provides integrations with SaaS API providers such as Facebook and Twitter. We can configure Spring Social by following these steps:

    1) Create the application context configuration class which implements the SocialConfigurer interface and annotate the created class with the @Configuration annotation. The SocialConfigurer interface declares callback methods which can be used to configure Spring Social.
    2) Annotate the class with the @EnableSocial annotation. This enables Spring Social and imports the SocialConfiguration configuration class.
    3) Add a DataSource field to the configuration class and annotate the field with the @Autowired annotation.
    4) Add the addConnectionFactories() method of the SocialConfigurer interface to the created configuration class. This method takes two method parameters which are described in the following:
        4.1) The first parameter is a ConnectionFactoryConfigurer object which can be used to register connection factories.
        4.2) The second parameter is an Environment object which represents the environment in which our example application is running.
    5) Implement the addConnectionFactories() method by following these steps:
        5.1) Create a new TwitterConnectionFactory object, and pass the consumer key and the consumer secret as constructor arguments.
        5.2) Register the created TwitterConnectionFactory object by calling the addConnectionFactory() method of the ConnectionFactoryConfigurer interface. Pass the created TwitterConnectionFactory object as a method parameter.
        5.3) Create a new FacebookConnectionFactory object, and pass the application id and the application secret as constructor arguments.
        5.4) Register the created FacebookConnectionFactory object by calling the addConnectionFactory method of the ConnectionFactoryConfigurer interface. Pass the created FacebookConnectionFactory object as a method parameter.
    6) Add the getUserIdSource() method of the SocialConfigurer interface to the created class. The UserIdSource object returned by this method is responsible of determining the correct account id of the user. Because our example application uses the username of the user as an account id, we have to implement this method by returning a new AuthenticationNameUserIdSource object.
    7) Add the getUsersConnectionRepository() method of the SocialConfigurer interface to the created class. This method takes a ConnectionFactoryLocator object as a method parameter and returns a UsersConnectionRepository object.
    8) Implement the getUsersConnectionRepository() method by following these steps:
        8.1) Create a new JdbcUsersConnectionRepository object and pass the following objects as constructor arguments:
            8.1.1) The first argument is a DataSource object. We pass the value of the dataSource field as the first method parameter.
            8.1.2) The second argument is a ConnectionFactoryLocator object. We pass the value of the connectionFactoryLocator method parameter as the second method parameter.
            8.1.3) The third parameter is a TextEncryptor object which encrypts the authorization details of the connection established between a SaaS API provider and our application. We create this object by calling the noOpText() method of the Encryptors class. This means that our example application stores these details as plaintext. This is handy during the development phase but we should not use it in production. 
        8.2) Return the created object.
    9) Configure the ConnectController bean. The method which configures this bean has two parameters. The first parameter is the ConnectionFactoryLocator bean. The second parameter is the used ConnectionRepository bean. Pass these parameters as constructor arguments when you are creating a new ConnectController object.

 * source: http://www.petrikainulainen.net/programming/spring-framework/adding-social-sign-in-to-a-spring-mvc-web-application-configuration/
 * 
 * @author Gustavo Orsi
 *
 */
//@formatter:on
@Configuration
@EnableSocial
public class SocialIntegrationConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		//@formatter:off
        cfConfig.addConnectionFactory(new TwitterConnectionFactory(
                env.getProperty("spring.social.twitter.consumer.key"),
                env.getProperty("spring.social.twitter.consumer.secret")
        ));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("spring.social.facebook.app.id"),
                env.getProperty("spring.social.facebook.app.secret")
        ));
        cfConfig.addConnectionFactory(new LinkedInConnectionFactory(
                env.getProperty("spring.social.linkedin.app.id"),
                env.getProperty("spring.social.linkedin.app.secret")
        ));
      //@formatter:on
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		//@formatter:off
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
      //@formatter:on
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}

}
