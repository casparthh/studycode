package thh.studycode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thh.studycode.entity.User;

import java.util.Calendar;

@RestController
public class UserController {

    private byte[] bytes = null;

    @GetMapping("/user/{id}")
    public User get(@PathVariable int id){
        bytes = new byte[1024*1024*100];
        User u = new User();
        u.setId(id);
        u.setName("name"+id);
        u.setAge(10+id);
        u.setBirthday(Calendar.getInstance().getTime());
        return u;
    }



}
