package com.ps.service.handler

import com.ps.service.pushNotofication.NotificationSender
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author pawel.gdula
 */
@Slf4j
class MessageHandler {

    @Autowired
    NotificationSender sender

    public void handleMessage(Map<String, ?> message) {
        try {
            Long customerId = message.customerId
            String text = message.text
            log.info('Sending message {} to customer {}', text, customerId)
            sender.send(customerId, text)
        } catch (Throwable e) {
            log.error("Cant send message ${message}", e)
        }
    }
}
