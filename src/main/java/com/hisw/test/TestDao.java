package com.hisw.test;

import com.hisw.dao.MongoTestDaoImpl;
import com.hisw.model.Student;

import java.util.List;

/**
 * Created by swallow on 2016/8/9.
 */
public class TestDao {
    public static void main(String[] args) {
        MongoTestDaoImpl mongoTestDao = new MongoTestDaoImpl();
//        mongoTestDao.save(new Student(11, "bbc", 21));
//        System.out.println("save complete");
//        if (mongoTestDao.update(new Student(2, "a", 1))){
//            System.out.println("update complete");
//        }
//        mongoTestDao.delete(new Student(10, "", 1));
//        System.out.println("delete complete");
        List<Student> students = mongoTestDao.list(1);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
