package sample;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;

@Configuration
@Profile("test")
public class TestJmsConfiguration {
	@Bean ConfigurationManager configurationManager() {
		return new ConfigurationManager();
	}
	
	@Bean DestinationManager destinationManager() {
		return new DestinationManager();
	}
	
	@Bean ConnectionFactory connectionFactory() {
		MockQueueConnectionFactory bean = new MockQueueConnectionFactory(
				 destinationManager(), configurationManager()
				);
		
		return bean;
	}
	
	@Bean Destination destination() {
		return destinationManager().createQueue("batchQueue");
	}
}
