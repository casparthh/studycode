package thh.studycode.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private int id;
    private String name;
    private String phone;
    private String email;
    private int age;
    private Date birthday;

}
