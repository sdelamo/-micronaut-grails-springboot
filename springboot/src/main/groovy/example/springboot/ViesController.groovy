package example.springboot

import eu.vies.VatService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RequestMapping('/vies')
@RestController
class ViesController {

    @Autowired
    VatService vatService

    @GetMapping('/valid')
    Boolean valid(@RequestParam(required = true) String memberStateCode,
                  @RequestParam(required = true) String vatNumberCode) {
        vatService.validateVat(memberStateCode, vatNumberCode)
    }
}
