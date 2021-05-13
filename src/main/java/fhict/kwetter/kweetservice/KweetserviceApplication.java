package fhict.kwetter.kweetservice;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KweetserviceApplication
{
	@Value("${spring.messaging.host}")
	String host;

	@Value("${spring.messaging.username}")
	String username;

	@Value("${spring.messaging.password}")
	String password;

	@Bean
	CachingConnectionFactory cachingConnectionFactory()
	{
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);

		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);

		return cachingConnectionFactory;
	}

	@Bean
	public MessageConverter jsonMessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

		rabbitTemplate.setMessageConverter(jsonMessageConverter());

		return rabbitTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(KweetserviceApplication.class, args);
	}
}
