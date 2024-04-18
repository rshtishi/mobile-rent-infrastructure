package com.github.rshtishi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

  @Value("${mobile-rent.notification.exchange}")
  private String notificationExchange;
  @Value("${mobile-rent.notification.routing-key}")
  private String routingKey;
  @Value("${mobile-rent.notification.qeue}")
  private String notificationQueue;

  @Bean
  public TopicExchange notificationExchange() {
    return new TopicExchange(notificationExchange);
  }

  @Bean
  public Queue notificationQueue() {
    return new Queue(notificationQueue);
  }

  @Bean
  public Binding bindingNotificationQueue(Queue notificationQueue, TopicExchange notificationExchange) {
    return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(routingKey);
  }

  @Bean
  public Jackson2JsonMessageConverter converter() {
    return new Jackson2JsonMessageConverter(new ObjectMapper());
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }
}
