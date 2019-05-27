package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ViesControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @AutoCleanup
    @Shared
    HttpClient httpClient = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }

    @Unroll
    def "#memberState : #vatNumber #unrollDescription"(String memberState, String vatNumber, Boolean expected, String unrollDescription) {
        given:
        String uri = UriBuilder.of('/vies/valid')
                .queryParam('memberStateCode', memberState)
                .queryParam('vatNumberCode', vatNumber)
                .build()

        expect:
        expected == Boolean.valueOf(client.retrieve(HttpRequest.GET(uri), String))

        where:
        memberState | vatNumber   || expected
        'es'        | 'B99286353' || true
        'es'        | 'B19280031' || true
        'es'        | 'XXXXXXXXX' || false

        unrollDescription = expected ? 'is valid' : ' is not valid'
    }
}
