package com.ps.service.pushNotification

/**
 * @author pawel.gdula
 */
class NotificationException extends RuntimeException {

    public final int responseCode

    NotificationException(String message) {
        super(message)
        responseCode = -1
    }

    NotificationException(Message message, int responseCode) {
        super("Error during sending message = ${message}, status = ${responseCode}")
        this.responseCode = responseCode
    }
}
