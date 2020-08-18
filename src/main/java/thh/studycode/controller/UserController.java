package thh.studycode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thh.studycode.entity.User;

//import springfox.documentation.spring.web.json.JsonSerializer;

@Slf4j
@RestController
public class UserController {

    private byte[] bytes = null;

//    @Autowired
//    private JsonSerializer jsonSerializer;

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
