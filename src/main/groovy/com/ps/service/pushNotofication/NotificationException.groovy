package com.ps.service.pushNotofication

/**
 * @author pawel.gdula
 */
class NotificationException extends RuntimeException {

    public final int responseCode

    NotificationException(String message) {
        super(message)
        responseCode = -1
    }

    NotificationException(Long customerId, int responseCode) {
        super("Error during sending notification for customer = ${customerId}, status = ${responseCode}")
        this.responseCode = responseCode
    }
}
