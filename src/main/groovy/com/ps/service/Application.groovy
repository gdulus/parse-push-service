package com.ps.service

import com.ps.service.config.QueueConfiguration
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

/**
 * @author pawel.gdula
 */
@ComponentScan
@EnableAutoConfiguration
class Application implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.convertAndSend(QueueConfiguration.QUEUE_NAME, [customerId: 666, text:'spring boot']);
    }
}
