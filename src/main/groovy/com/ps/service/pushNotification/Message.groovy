package com.ps.service.pushNotification

/**
 * @author pawel.gdula
 */
class Message {

    final Long customerId

    final String text

    public Message(Map<String, ?> serializedMessage) {
        customerId = serializedMessage.customerId
        text = serializedMessage.text
    }

    public boolean valid() {
        return customerId && text
    }

    @Override
    public String toString() {
        return "Message{" +
                "customerId=" + customerId +
                ", text=" + text +
                '}';
    }
}
