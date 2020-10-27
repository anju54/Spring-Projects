package com.amigo.producerrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amigo.producerrabbitmq.constants.MessagingConstants;

@Configuration
public class MessageConfig {

	@Bean
	Queue queue(){
		return new Queue(MessagingConstants.QUEUE);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(MessagingConstants.EXCHANGE);
	}
	
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		
		return BindingBuilder.bind(queue).to(exchange).with(MessagingConstants.ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
