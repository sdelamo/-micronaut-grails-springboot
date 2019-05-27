package example.grails

import grails.testing.mixin.integration.Integration
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import spock.lang.Specification
import spock.lang.Unroll

@Integration
class ViesControllerSpec extends Specification {

    @Unroll
    def "#memberState : #vatNumber #unrollDescription"(String memberState, String vatNumber, Boolean expected, String unrollDescription) {

        given:
        BlockingHttpClient client = HttpClient.create(new URL("http://localhost:$serverPort".toString())).toBlocking()
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
