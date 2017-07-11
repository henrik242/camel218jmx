package hello;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.ExplicitCamelContextNameStrategy;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class SomeApp extends CamelConfiguration implements InitializingBean {

    private static final Logger LOG = Logger.getLogger(SomeApp.class.getName());

    private static String mgmtName = "unset";

    @Autowired
    CamelContext camelContext;

    @Override
    protected void setupCamelContext(CamelContext camelContext) {
        camelContext.setUseMDCLogging(false);
        camelContext.setStreamCaching(true);
        camelContext.setAllowUseOriginalMessage(false);
        camelContext.setNameStrategy(new ExplicitCamelContextNameStrategy("my-name"));
        camelContext.getShutdownStrategy().setTimeout(10);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.severe("mbean name: " + camelContext.getManagementName());
        mgmtName = camelContext.getManagementName();
    }

    @Bean(name = "afterProps")
    public String fromAfterProps() {
        return mgmtName;
    }

    @Bean(name = "cmlCntxt")
    public String fromCamelContext() {
        return camelContext.getManagementName();
    }


    public static void main(String[] args) {
        SpringApplication.run(SomeApp.class, args);
    }
}
