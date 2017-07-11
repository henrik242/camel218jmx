package hello

import org.apache.camel.CamelContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = SomeApp)
class SomeTest extends Specification {

    @Autowired
    CamelContext camelContext

    @Autowired
    @Qualifier("afterProps")
    String afterProps

    @Autowired
    @Qualifier("cmlCntxt")
    String cmlCntxt

    def "managementname directly from context"() {
        expect:
        camelContext.getManagementName() == "my-name"
    }

    def "managementname via afterpropertiesset"() {  // FAILS with 2.18.0
        expect:
        afterProps == "my-name"  // will be "unset" if OtherConfig hasn't tried setting the value
    }

    def "managementname via camelContext"() { // FAILS with 2.18.0
        expect:
        cmlCntxt == "my-name"
    }

}