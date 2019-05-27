package example.micronaut

import eu.vies.VatService
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class VatServiceBeanExistsSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    void "bean VatService exists"() {
        expect:
        applicationContext.containsBean(VatService)
    }
}
