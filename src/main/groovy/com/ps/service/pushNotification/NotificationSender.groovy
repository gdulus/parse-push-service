package com.ps.service.pushNotification

/**
 * @author pawel.gdula
 */
public interface NotificationSender {

    /**
     * Allows to send push notification to customer
     * Implementation of this method should communicate about errors by throwing {@link NotificationException}
     * @param customerId Id of customer which should retrieve notification
     * @param notification Message to send
     */
    public void send(final Message message)
}