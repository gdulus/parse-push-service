package com.ps.service.handler

import com.ps.service.pushNotification.Message
import com.ps.service.pushNotification.NotificationSender
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author pawel.gdula
 */
@Slf4j
class MessageHandler {

    @Autowired
    NotificationSender sender

    public void handleMessage(Map<String, ?> serializedMessage) {
        try {
            Message message = new Message(serializedMessage)

            if (!message.valid()) {
                log.error('Message invalid {}', serializedMessage)
                return
            }

            log.info('Sending message {}', message)
            sender.send(message)
        } catch (Throwable e) {
            log.error("Cant send message ${message}", e)
        }
    }
}

