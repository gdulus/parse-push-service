package com.ps.service.pushNotification

import groovy.util.logging.Slf4j
import groovyx.net.http.HTTPBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST

/**
 * @author pawel.gdula
 */
/**
 * Implementation on NotificationProvider using as a platform Parse (https://www.parse.com)
 * @author pawel.gdula
 */
@Slf4j
@Component
class ParseNotificationSender implements NotificationSender {

    @Value('${notification.parse.apiURL}')
    public String apiURL

    @Value('${notification.parse.apiURI}')
    public String apiURI

    @Value('${notification.parse.applicationId}')
    public String applicationId

    @Value('${notification.parse.apiKey}')
    public String apiKey

    @PostConstruct
    public void audit(){
        log.info("Setting up parse sender with configuration apiURL = {}, apiURI = {}, applicationId = {}, apiKey = {}", apiURL, apiURI, applicationId, apiKey)
    }

    @Override
    public void send(final Long customerId, final String notification) {
        if (!customerId || !notification) {
            throw new NotificationException('Not null parameter required')
        }

        HTTPBuilder.request(POST) {
            uri.path = apiURI
            headers['X-Parse-Application-Id'] = applicationId
            headers['X-Parse-REST-API-Key'] = apiKey
            requestContentType = JSON
            body = getBody(customerId, notification)

            response.success = { resp ->
                log.info('Message sent successfully to customer {} "{}"', customerId, notification)
            }

            response.failure = { resp ->
                log.error('Message sent error to customer {} "{}"', customerId, notification)
                int responseStatus = resp.status
                throw new NotificationException(customerId, responseStatus)
            }
        }
    }

    private HTTPBuilder getHTTPBuilder() {
        return new HTTPBuilder(apiURL)
    }

    private Map<String, ?> getBody(final Long customerId, final String notification) {
        // we need to call toString on GString object - in other case GString object will be serialized
        [channels: ["customerID-${customerId}".toString()], data: [alert: notification]]
    }
}
