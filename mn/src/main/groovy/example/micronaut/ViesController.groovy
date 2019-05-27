package example.micronaut

import eu.vies.VatService
import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@CompileStatic
@Controller('/vies')
class ViesController {

    @Inject
    VatService vatService

    @Get('/valid')
    Boolean valid(String memberStateCode, String vatNumberCode) {
        vatService.validateVat(memberStateCode, vatNumberCode)
    }
}
