package thh.studycode.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String name;
    private String phone;
    private String email;
    private int age;
    private Date birthday;


}
