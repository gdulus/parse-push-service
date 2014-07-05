package com.ps.service.config

import com.ps.service.handler.MessageHandler
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author pawel.gdula
 */
@Configuration
class QueueConfiguration {

    final static String EXCHANGE_NAME = "com.ps.direct";

    final static String QUEUE_NAME = "com.ps.direct.notification.push";

    @Value('${amqp.host}')
    public String host

    @Value('${amqp.username}')
    public String username

    @Value('${amqp.password}')
    public String password

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.username = username
        connectionFactory.password = password
        return connectionFactory
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageHandler consumer() {
        return new MessageHandler();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageHandler consumer) {
        return new MessageListenerAdapter(consumer, "handleMessage");
    }
}

