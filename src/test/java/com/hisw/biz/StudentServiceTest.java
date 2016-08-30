package com.hisw.biz;

import com.hisw.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by swallow on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/SpringMVC-servlet.xml")
//@ContextConfiguration("file:src/main/resources/SpringMVC-servlet.xml")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Test
    public void list() {
        List list = studentService.list();
        assertEquals(22, list.size());
    }

    @Test
    @Transactional
    public void save() {
        Student student = new Student(122, "zxc", 30);
        int i = studentService.save(student);
        assertEquals(1, i);
    }

    @Test
    @Transactional
    public void update() {
        Student student = new Student(101, "zxc", 30);
        int i = studentService.update(student);
        assertEquals(1, i);
    }

    @Test
    @Transactional
    public void delete() {
        Student student = new Student(101, "zxc", 30);
        int i = studentService.delete(student);
        assertEquals(1, i);
    }
}
