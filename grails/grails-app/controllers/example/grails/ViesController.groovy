package example.grails

import eu.vies.VatService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
class ViesController {

    @Autowired
    VatService vatService

    def valid(String memberStateCode, String vatNumberCode) {
        render vatService.validateVat(memberStateCode, vatNumberCode)
    }
}
