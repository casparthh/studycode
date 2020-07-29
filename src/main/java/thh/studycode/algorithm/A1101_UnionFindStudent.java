package thh.studycode.algorithm;

import java.util.*;

/**
 * 如果两个学生，如果id一样，或姓名一样，或手机号一样。就认为是一个人
 */
public class A1101_UnionFindStudent {

    static class Student{
        String id;
        String name;
        String phone;
        Student self;

        public Student(String id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            self = this;
        }
    }

    static Map<String, Student> idMap = new HashMap<String, Student>();
    static Map<String, Student> nameMap = new HashMap<String, Student>();
    static Map<String, Student> phoneMap = new HashMap<String, Student>();

    public static void add(Student stu){
        if(idMap.containsKey(stu.id)){
            stu.self = idMap.get(stu.id);
            return;
        } else {
            idMap.put(stu.id, stu);
        }
        if(nameMap.containsKey(stu.name)){
            stu.self = nameMap.get(stu.name);
            return;
        } else {
            nameMap.put(stu.name, stu);
        }
        if(phoneMap.containsKey(stu.phone)){
            stu.self = phoneMap.get(stu.phone);
            return;
        }else{
            phoneMap.put(stu.phone, stu);
        }
    }

}
