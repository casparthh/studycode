package thh.studycode.drools;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import thh.studycode.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public class Drools01Test {

    @Test
    public void test01() {
        User user = new User();
        user.setId(2);
        user.setName("test");
        user.setPhone("12345");
        user.setEmail("2345");
        user.setAge(45);
        user.setBirthday(new Date());
        user.setHobby(new String[]{"book","tv"});


        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        // session 如果相同，想要改变数据需要使用kieContainer的update方法，并在之后使用dispose
        KieSession kieSession = kieClasspathContainer.newKieSession();
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.dispose();

    }
}
