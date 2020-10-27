package com.amigo.producerrabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigo.producerrabbitmq.constants.MessagingConstants;

@RestController
@RequestMapping("/rabbitmq")
public class ProducerController {

	@Autowired
	private AmqpTemplate template;
	
	@GetMapping(value = "/test")
	public String testing() {
		String msg = "From producer !!!!";
		template.convertAndSend(MessagingConstants.EXCHANGE,MessagingConstants.ROUTING_KEY,msg);
		return msg;
	}
}
