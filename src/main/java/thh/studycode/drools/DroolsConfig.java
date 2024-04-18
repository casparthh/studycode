package thh.studycode.drools;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DroolsConfig {
    public static final String RULES_DRL = "drools/rule01.drl";
//    public static KieServices kieServices = KieServices.Factory.get();

//    @Bean
//    public KieContainer kieContainer(){
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_DRL));
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();

//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
//        kieBuilder.buildAll();
//        KieModule kieModule = kieBuilder.getKieModule();
//        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//        return kieContainer;
//    }
}
