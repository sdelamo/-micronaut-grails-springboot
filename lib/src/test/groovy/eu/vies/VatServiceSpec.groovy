package eu.vies

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class VatServiceSpec extends Specification {

    @Subject
    @Shared
    VatService service = new VatService()

    @Unroll
    def "#memberState : #vatNumber #unrollDescription"(String memberState, String vatNumber, Boolean expected, String unrollDescription) {

        expect:
        expected == service.validateVat(memberState, vatNumber)

        where:
        memberState | vatNumber   || expected
        'es'        | 'B99286353' || true
        'es'        | 'B19280031' || true
        'es'        | 'XXXXXXXXX' || false

        unrollDescription = expected ? 'is valid' : ' is not valid'
    }
}