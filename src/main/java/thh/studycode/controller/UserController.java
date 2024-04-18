package thh.studycode.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thh.studycode.entity.User;

import java.util.Date;

//import springfox.documentation.spring.web.json.JsonSerializer;

@Slf4j
@RestController
public class UserController {

    private byte[] bytes = null;

//    @Autowired
//    private JsonSerializer jsonSerializer;

//    @Autowired
//    private KieContainer kieContainer;

    @GetMapping("/drools")
    public ResponseEntity drools(){
        User user = new User();
        user.setId(2);
        user.setName("test");
        user.setPhone("12345");
        user.setEmail("2345");
        user.setAge(45);
        user.setBirthday(new Date());
        user.setHobby(new String[]{"book","tv"});

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.dispose();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable int id){
        bytes = new byte[1024*1024*100];
        User u = new User();
//        u.setId(id);
        u.setName("name"+id);
        u.setAge(10+id);
//        u.setBirthday(Calendar.getInstance().getTime());
//        log.info("user: {}",jsonSerializer.toJson(u).value());
        return u;
    }



}
